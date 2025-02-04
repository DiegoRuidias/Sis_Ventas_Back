package com.system.ventas.repository;

import com.system.ventas.model.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SalesRepository extends JpaRepository<Sales, Integer> {
    @Query("SELECT s FROM Sales s JOIN FETCH s.customer JOIN FETCH s.user JOIN FETCH s.salesDetails")
    List<Sales> findAllByDate();
}
