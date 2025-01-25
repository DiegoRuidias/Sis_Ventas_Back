package com.system.ventas.service;


import com.system.ventas.model.dto.MenuUserDTO;
import com.system.ventas.model.dto.MenusDTO;
import com.system.ventas.model.entities.Menus;

import java.util.List;

public interface MenusService {

    Menus create(Menus menus);
    List<MenusDTO> findAll();
    List<MenuUserDTO>  getMenuByTypePerson(Integer typePerson);
    
} 
