package com.system.ventas.repository;

import com.system.ventas.model.entities.Products;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Products, Integer> {
}
