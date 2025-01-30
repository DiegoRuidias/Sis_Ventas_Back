package com.system.ventas.controller.system;

import com.system.ventas.model.dto.ProductEntriesDTO;
import com.system.ventas.model.entities.ProductEntries;
import com.system.ventas.service.ProductEntriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/system/product-entries")
public class ProductEntriesController {
    @Autowired
    private ProductEntriesService productEntriesService;

    @PostMapping
    public ResponseEntity<Boolean> create(@Valid @RequestBody ProductEntriesDTO request){
        productEntriesService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping
    public ResponseEntity<List<ProductEntries>> findAllByDate(
            @RequestParam (required = false) LocalDateTime date,
            @RequestParam (required = false) String storeId) {
        List<ProductEntries> response = productEntriesService.findByDateAndStore(date, storeId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
