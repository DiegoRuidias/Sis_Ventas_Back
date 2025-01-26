package com.system.ventas.repository;

import com.system.ventas.model.entities.Sales;
import org.springframework.data.repository.CrudRepository;

public interface SalesRepository extends CrudRepository<Sales, Integer> {
}
