package com.system.ventas.controller.auth;

import com.system.ventas.controller.request.UserDTO;
import com.system.ventas.model.entities.UserRoles;
import com.system.ventas.model.entities.Users;
import com.system.ventas.repository.UserRolesRepository;
import com.system.ventas.service.UsersService;
import com.system.ventas.util.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private UsersService userService;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO user) {
        Users existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            if(!existingUser.getIsActive()) {
                Map<String, Object> response = new HashMap<>();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            Integer userId = existingUser.getId();
            UserRoles userRoles = userRolesRepository.findByUserId(userId);
            if (userRoles == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Rol de usuario Desactivado");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            Integer role = userRoles.getRole().getTypePerson();
            String name = existingUser.getName();
            String token = jwtUtil.generateToken(existingUser.getUsername(),role,userId,name);
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "expiresAt", jwtUtil.getClaims(token).getExpiration(),
                    "role", role,
                    "userId", userId,
                    "name", name
            ));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Credenciales inválidas");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
