package com.system.ventas.repository;

import com.system.ventas.model.entities.ProductEntries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntriesRepository extends JpaRepository<ProductEntries, Integer> {
}
