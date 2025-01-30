package com.system.ventas.repository;

import com.system.ventas.model.entities.ProductEntries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductEntriesRepository extends JpaRepository<ProductEntries, Integer> {
    @Query("SELECT e FROM ProductEntries e JOIN FETCH e.provider p JOIN FETCH e.store s WHERE (e.date >= :start AND e.date < :end) AND (s.id=:storeId) order by e.date DESC")
    List<ProductEntries> findByStoreAndDate(LocalDateTime start, LocalDateTime end, String storeId);
}
