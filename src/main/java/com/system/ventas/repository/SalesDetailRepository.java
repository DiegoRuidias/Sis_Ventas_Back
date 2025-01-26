package com.system.ventas.repository;

import com.system.ventas.model.entities.SalesDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesDetailRepository extends JpaRepository<SalesDetail, Integer> {
}
