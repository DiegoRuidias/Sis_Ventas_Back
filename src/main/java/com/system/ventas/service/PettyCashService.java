package com.system.ventas.service;

import com.system.ventas.model.dto.PettyCashDTO;
import com.system.ventas.model.entities.PettyCash;

import java.math.BigDecimal;
import java.util.List;

public interface PettyCashService {
    List<PettyCash> findAll();
    PettyCash create(PettyCashDTO pettyCashDTO);
    void endPetty(Integer id);
    void deletePetty(Integer id);
    Boolean activeById(Integer id);
    void update (Integer userId, BigDecimal totalPos, BigDecimal totalCash);
}
