package com.system.ventas.controller.system;

import com.system.ventas.model.dto.ProductDTO;
import com.system.ventas.model.dto.ReorderIntDTO;
import com.system.ventas.model.entities.Products;
import com.system.ventas.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/system/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Boolean> create(@Valid @RequestBody ProductDTO request) {
        productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PatchMapping("/reorder")
    public ResponseEntity<Boolean> reorder(@Valid @RequestBody Set<ReorderIntDTO> request) {
        productService.reorder(request);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<List<Products>> create(@PathVariable String storeId) {
        List<Products> response = productService.findByStoreId(storeId);
        return ResponseEntity.ok(response);
    }
}
