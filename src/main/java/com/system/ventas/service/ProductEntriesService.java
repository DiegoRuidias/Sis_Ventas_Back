package com.system.ventas.service;

import com.system.ventas.model.dto.ProductEntriesDTO;
import com.system.ventas.model.entities.ProductEntries;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductEntriesService {
    void create(ProductEntriesDTO productEntries);
    List<ProductEntries> findByDateAndStore(LocalDateTime date, String storeId);
}
