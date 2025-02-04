package com.system.ventas.service;

import com.system.ventas.model.dto.SalesDTO;
import com.system.ventas.model.entities.Sales;

import java.util.List;

public interface SalesService {
    void create (SalesDTO sales);
    List<Sales> findByDate();
}
