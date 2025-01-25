package com.system.ventas.controller.security;

import com.system.ventas.controller.request.PasswordDTO;
import com.system.ventas.model.dto.UsersDTO;
import com.system.ventas.model.entities.Users;
import com.system.ventas.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/security/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<UsersDTO>> findAll(){
        List<UsersDTO> list = usersService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Users> create (@Validated @RequestBody Users user ){
        Users request = usersService.create(user);
        return ResponseEntity.ok().body(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> update (@Validated @RequestBody Users request ){
        Users response = usersService.update(request);
        return ResponseEntity.ok().body(response);
    }


    @PatchMapping("/{id}/{isActive}")
    public ResponseEntity<Boolean> updateByIsActive(@PathVariable String id, @PathVariable Boolean isActive ) {
        usersService.updateIsActiveById(isActive , id);
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/password")
    public ResponseEntity<Boolean> ChangePassword(@RequestBody PasswordDTO request) {
        usersService.updatePassword(request);
        return ResponseEntity.ok(true);
    }

}