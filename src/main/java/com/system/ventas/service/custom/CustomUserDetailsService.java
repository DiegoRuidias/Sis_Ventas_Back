package com.system.ventas.service.custom;

import com.system.ventas.model.entities.UserRoles;
import com.system.ventas.model.entities.Users;
import com.system.ventas.repository.UserRolesRepository;
import com.system.ventas.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersService userService; // Usa tu servicio que interactúa con la base de datos

    @Autowired
    private UserRolesRepository userRolesRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        UserRoles userRoles = userRolesRepository.findByUserId(user.getId());

        GrantedAuthority authority = new SimpleGrantedAuthority(mapRoleIdToRoleName(userRoles.getRole().getTypePerson()));

        // Crear un UserDetails con username, password y autoridad
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getIsActive(), // habilitado/deshabilitado
                true,            // cuenta no expirada
                true,            // credenciales no expiradas
                true,            // cuenta no bloqueada
                List.of(authority)
        );
    }

    // Mapear el ID del rol al nombre del rol
    private String mapRoleIdToRoleName(Integer roleId) {
        return switch (roleId) {
            case 1 -> "ROLE_VENDOR";
            case 4 -> "ROLE_ADMIN";
            case 5 -> "ROLE_SADMIN";
            default -> throw new IllegalArgumentException("ID de rol desconocido: " + roleId);
        };
    }
}
