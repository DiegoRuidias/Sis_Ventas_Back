package com.system.ventas.service;

import com.system.ventas.model.entities.Roles;

import java.util.List;

public interface RolesService {

    Roles create (Roles roles);

    Roles update (Roles roles);

    void updateIsActiveById(Boolean isActive, String id);

    List<Roles> findAll ();

    void deletedById(String id);
}
