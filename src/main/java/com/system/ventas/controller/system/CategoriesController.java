package com.system.ventas.controller.system;

import com.system.ventas.model.entities.Categories;
import com.system.ventas.service.CategoriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/system/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
     public ResponseEntity<List<Categories>> findAll() {
        List<Categories> response = categoriesService.findAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Categories> create(@Valid @RequestBody Categories request) {
        Categories response = categoriesService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categories> update(@Valid @RequestBody Categories request) {
        Categories response = categoriesService.update(request);
        return ResponseEntity.ok(response);
    }
}
