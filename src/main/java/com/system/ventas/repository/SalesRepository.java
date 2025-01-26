package com.system.ventas.repository;

import com.system.ventas.model.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SalesRepository extends JpaRepository<Sales, Integer> {
}
