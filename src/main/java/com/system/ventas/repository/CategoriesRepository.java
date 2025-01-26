package com.system.ventas.repository;

import com.system.ventas.model.entities.Categories;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Categories, String> {
}
