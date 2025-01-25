package com.system.ventas.service.Impl;

import com.system.ventas.model.dto.UserRolesDTO;
import com.system.ventas.model.entities.Roles;
import com.system.ventas.model.entities.UserRoles;
import com.system.ventas.model.entities.Users;
import com.system.ventas.repository.RolesRepository;
import com.system.ventas.repository.UserRolesRepository;
import com.system.ventas.repository.UsersRepository;
import com.system.ventas.service.UsersRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("userRolesServiceImpl")
public class UserRolesServiceImpl implements UsersRolesService {
    @Autowired
    UserRolesRepository userRolesRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<UserRolesDTO> findAllByUser(Integer userId) {
        if (!usersRepository.existsById(userId)) {
            throw new RuntimeException("Id del Usuario no existe: " + userId);
        }

        List<Roles> roles = rolesRepository.findAllRoles();
        Map<String, UserRoles> userRolesMap = userRolesRepository.findAllByUserId(userId)
                .stream()
                .collect(Collectors.toMap(ur -> ur.getRole().getId(), ur -> ur));

        return roles.stream()
                .map(role -> {
                    UserRoles userRole = userRolesMap.get(role.getId());
                    return UserRolesDTO.builder()
                            .id(userRole != null ? userRole.getId() : null )
                            .roleId(role.getId())
                            .userId(userId)
                            .isActive(userRole != null ? userRole.getIsActive() : false)
                            .createdAt(userRole != null ? userRole.getCreatedAt() : null)
                            .roleName(role.getName())
                            .roleDescription(role.getDescription())
                            .typePerson(role.getTypePerson())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<UserRolesDTO> create(Integer userId, List<UserRolesDTO> userRoles) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Id del Usuario no exite: " + userId));

        List<UserRoles> userRolesEntities = userRoles.stream()
                .map(urdto -> {
                    Roles roles = rolesRepository.findById( urdto.getRoleId())
                            .orElseThrow(() -> new RuntimeException("Id del rol no existe: " + urdto.getRoleId()));
                    return UserRoles.builder()
                            .id(urdto.getId())
                            .user(users)
                            .role(roles)
                            .isActive(urdto.getIsActive())
                            .createdAt(urdto.getCreatedAt())
                            .build();
                })
                .collect(Collectors.toList());
        userRolesRepository.saveAll(userRolesEntities);
        return userRoles;
    }
    @Transactional
    @Override
    public void createUnique(Integer userId, String roleId) {
        usersRepository.findById(userId);
        rolesRepository.findById(roleId);
        UserRoles search = userRolesRepository.findByUserIdAndRoleId(userId,roleId);
        if(search == null) {
            userRolesRepository.updateIsActive(userId);
            UserRoles userRoles = new UserRoles();
            userRoles.setId(UUID.randomUUID().toString());
            userRoles.setUser(usersRepository.findById(userId).get());
            userRoles.setIsActive(true);
            userRoles.setRole(rolesRepository.findById(roleId).get());
            userRolesRepository.save(userRoles);
        }

    }



}
