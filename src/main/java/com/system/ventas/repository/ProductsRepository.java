package com.system.ventas.repository;

import com.system.ventas.model.entities.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
    @Query("SELECT p FROM Products p JOIN p.store s WHERE (s.id = :storeId AND p.deletedAt is null ) ORDER BY p.sort")
    List<Products> findProductsByStoreId(String storeId);


    @Query(value = "SELECT p FROM Products p JOIN p.store s WHERE s.id = :storeId AND p.deletedAt is null AND s.isActive = true ",
            countQuery = "SELECT COUNT(p) FROM Products p JOIN p.store s WHERE s.id = :storeId AND p.deletedAt is null AND s.isActive = true")
    Page<Products> find(Pageable pageable, String storeId);

    @Query(value = "SELECT p FROM Products p JOIN p.store s WHERE (s.id = :storeId) AND (p.deletedAt is null) AND (p.isActive = true) AND (p.searchText LIKE CONCAT('%', :searchText, '%'))",
    countQuery = "SELECT COUNT(p) FROM Products p JOIN p.store s WHERE (s.id = :storeId) AND (p.deletedAt is null) AND (p.isActive = true) AND (p.searchText LIKE CONCAT('%', :searchText, '%'))")
    Page<Products> findBySearchText(Pageable pageable, String storeId , String searchText);

    @Modifying
    @Query("UPDATE Products p SET p.stock = p.stock - :stock WHERE p.id = :id AND p.stock >= :stock")
    int updateStock(Integer id, BigDecimal stock);

    @Modifying
    @Query("UPDATE Products p SET p.stock = p.stock - (CAST(:boxes AS bigdecimal) * p.measureBox), p.boxes = p.boxes - :boxes WHERE p.id = :id AND p.boxes >= :boxes")
    int updateBox(Integer id, Integer boxes);
}
