package com.system.ventas.service;

import com.system.ventas.model.dto.UserRolesDTO;

import java.util.List;

public interface UsersRolesService {
    List<UserRolesDTO> findAllByUser ( Integer userId );
    List<UserRolesDTO> create (Integer userId, List<UserRolesDTO> userRoles);
    void createUnique (Integer userId, String roleId );

}
