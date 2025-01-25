package com.system.ventas.controller.security;

import com.system.ventas.model.dto.UserRolesDTO;
import com.system.ventas.service.UsersRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/security/user-roles")
public class UserRolesController {
    @Autowired
    UsersRolesService usersRolesService;

    @GetMapping("/{idUser}")
    public ResponseEntity<List<UserRolesDTO>> findAll(@PathVariable Integer idUser){
        List<UserRolesDTO> list = usersRolesService.findAllByUser(idUser);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<List<UserRolesDTO>> create(@PathVariable Integer idUser,@RequestBody List<UserRolesDTO> userRoles){
        List<UserRolesDTO> list = usersRolesService.create(idUser, userRoles);
        return ResponseEntity.ok().body(list);
    }
}
