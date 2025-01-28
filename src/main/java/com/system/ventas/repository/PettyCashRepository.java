package com.system.ventas.repository;

import com.system.ventas.model.entities.PettyCash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PettyCashRepository extends JpaRepository<PettyCash, Integer> {
    @Query("SELECT p FROM PettyCash p WHERE p.deletedAt IS NULL")
    List<PettyCash> findAllPettyCash();
}
