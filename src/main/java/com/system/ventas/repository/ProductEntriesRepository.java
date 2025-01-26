package com.system.ventas.repository;

import com.system.ventas.model.entities.ProductEntries;
import org.springframework.data.repository.CrudRepository;

public interface ProductEntriesRepository extends CrudRepository<ProductEntries, Integer> {
}
