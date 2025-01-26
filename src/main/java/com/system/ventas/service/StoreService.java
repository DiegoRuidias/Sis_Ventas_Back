package com.system.ventas.service;

import com.system.ventas.model.entities.Store;

import java.util.List;

public interface StoreService {
    List<Store> findAll();
    Store save(Store store);
    Store update(Store store);
}
