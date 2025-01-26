package com.system.ventas.service;

import com.system.ventas.model.entities.Categories;

import java.util.List;

public interface CategoriesService {
    List<Categories> findAll();
    Categories save(Categories categories);
    Categories update(Categories categories);
}
