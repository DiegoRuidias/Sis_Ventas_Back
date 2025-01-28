package com.system.ventas.service;

import com.system.ventas.model.dto.PettyCashDTO;
import com.system.ventas.model.entities.PettyCash;

import java.util.List;

public interface PettyCashService {
    List<PettyCash> findAll();

    PettyCash create(PettyCashDTO pettyCashDTO);

    void endPetty(Integer id);

    void deletePetty(Integer id);
}
