package com.system.ventas.service;

import com.system.ventas.model.dto.ReorderDTO;
import com.system.ventas.model.entities.Store;

import java.util.List;
import java.util.Set;

public interface StoreService {
    List<Store> findAll();
    Store save(Store store);
    Store update(Store store);
    void reorder(Set<ReorderDTO> reorder);
}
