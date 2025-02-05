package com.system.ventas.repository;
import com.system.ventas.model.entities.Sales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface SalesRepository extends JpaRepository<Sales, Integer> {
    @Query("SELECT s FROM Sales s JOIN FETCH s.customer JOIN FETCH s.user JOIN FETCH s.salesDetails WHERE (s.date >= :start AND s.date < :end) order by s.date DESC")
    List<Sales> findAllByDate(LocalDateTime start, LocalDateTime end);

    @Query(value = "SELECT s FROM Sales s JOIN FETCH s.customer JOIN FETCH s.user JOIN FETCH s.salesDetails WHERE (s.date >= :start AND s.date < :end) order by s.date DESC",
            countQuery = "SELECT COUNT(s) FROM Sales s WHERE (s.date >= :start AND s.date < :end)")
    Page<Sales> findPagedSales(Pageable pageable, LocalDateTime start, LocalDateTime end);
}
