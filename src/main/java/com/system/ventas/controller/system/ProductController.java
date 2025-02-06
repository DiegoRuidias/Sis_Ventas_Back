package com.system.ventas.controller.system;

import com.system.ventas.model.dto.ProductDTO;
import com.system.ventas.model.dto.ReorderIntDTO;
import com.system.ventas.model.entities.Products;
import com.system.ventas.model.entities.Store;
import com.system.ventas.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedModel;
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
    public ResponseEntity<List<Products>> findByStore(@PathVariable String storeId) {
        List<Products> response = productService.findByStoreId(storeId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/store")
    public ResponseEntity<PagedModel<Products>> findByStorePaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String storeId,
            @RequestParam(required = false) String searchText ) {
        PagedModel<Products> response = productService.findByStoreIdPaged(page, size, storeId, searchText);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@Valid @RequestBody ProductDTO request) {
        productService.update(request);
        return ResponseEntity.ok(true);
    }
}
