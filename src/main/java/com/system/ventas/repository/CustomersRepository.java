package com.system.ventas.repository;

import com.system.ventas.model.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Integer> {
}
