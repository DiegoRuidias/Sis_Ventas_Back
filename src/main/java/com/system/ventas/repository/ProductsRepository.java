package com.system.ventas.repository;

import com.system.ventas.model.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
    @Query("SELECT p FROM Products p JOIN p.store s WHERE (s.id = :storeId AND p.deletedAt is null ) ORDER BY p.sort")
    List<Products> findProductsByStoreId(String storeId);
}
