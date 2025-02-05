package com.system.ventas.repository;

import com.system.ventas.model.entities.PettyCash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PettyCashRepository extends JpaRepository<PettyCash, Integer> {
    @Query("SELECT p FROM PettyCash p JOIN FETCH p.user WHERE p.deletedAt IS NULL")
    List<PettyCash> findAllPettyCash();

    @Query("SELECT COUNT(p) FROM PettyCash p JOIN p.user u WHERE (p.deletedAt IS NULL) AND (p.isActive = true) AND (u.id =:userId)")
    int findPettyCashActive(Integer userId);

    @Modifying
    @Query("UPDATE PettyCash p SET p.totalPos = p.totalPos + :totalPos, p.totalCash = p.totalCash + :totalCash WHERE (p.deletedAt IS NULL) AND (p.isActive = true) AND (p.user.id =:userId)")
    int updateAmounts(Integer userId, BigDecimal totalPos, BigDecimal totalCash);
}
