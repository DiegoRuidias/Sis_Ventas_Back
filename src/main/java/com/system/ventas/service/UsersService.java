package com.system.ventas.service;

import com.system.ventas.controller.request.PasswordDTO;
import com.system.ventas.model.dto.UsersDTO;
import com.system.ventas.model.entities.Users;

import java.util.List;

public interface UsersService {
    Users create (Users user);
    List<UsersDTO> findAll();
    void updateIsActiveById(Boolean isActive, String id);
    Users update (Users user);
    Users findByUsername (String username);
    void updatePassword (PasswordDTO passwordDTO);
}
