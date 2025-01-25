package com.system.ventas.service.Impl;

import com.system.ventas.controller.request.PasswordDTO;
import com.system.ventas.exception.BusinessException;
import com.system.ventas.model.dto.UsersDTO;
import com.system.ventas.model.entities.Users;
import com.system.ventas.repository.UsersRepository;
import com.system.ventas.service.UsersService;
import jakarta.transaction.Transactional;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userServiceImpl")
public class UserServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Users create(Users user) {
        validateUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword().trim()));
        return usersRepository.save(user);
    }

    private void validateUsername (String username) {
        Set<String> response = new HashSet<String>();
        List<Users> users = usersRepository.findAllByUsername(username);
        if (!CollectionUtils.isEmpty(users)) {
            response.add(messageSource.getMessage("user.exist", new Object[]{username}, Locale.of("es")));
            throw new BusinessException(response);
        }
    }
    @Override
    @Transactional
    public Users update(Users users) {
        Users entity = usersRepository.getReferenceById(users.getId());
        entity.setName(users.getName());
        entity.setIsActive(users.getIsActive());
        entity.setUsername(users.getUsername());
        entity.setPhone(users.getPhone());
        entity.setEmail(users.getEmail());
        return entity;
    }

    @Override
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void updatePassword(PasswordDTO passwordDTO) {
        Users entity = usersRepository.getReferenceById(passwordDTO.getId());
        entity.setPassword(passwordEncoder.encode(passwordDTO.getPassword().trim()));
    }

    @Override
    public List<UsersDTO> findAll() {
        List<Users> users = usersRepository.findAll();
        return users.stream()
                .map(user -> {
                    return UsersDTO.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .name(user.getName())
                            .email(user.getEmail())
                            .isActive(user.getIsActive())
                            .phone(user.getPhone())
                            .createdAt(user.getCreatedAt())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void updateIsActiveById(Boolean isActive, String id) {
        usersRepository.updateIsActiveById(isActive, id);
    }
}