package com.system.ventas.repository;

import com.system.ventas.model.entities.PettyCash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PettyCashRepository extends JpaRepository<PettyCash, Integer> {
}
