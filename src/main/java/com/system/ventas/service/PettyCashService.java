package com.system.ventas.service;

import com.system.ventas.model.entities.PettyCash;

import java.util.List;

public interface PettyCashService {
    List<PettyCash> findAll();

    PettyCash create(PettyCash pettyCash);
}
