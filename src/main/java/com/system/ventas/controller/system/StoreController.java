package com.system.ventas.controller.system;

import com.system.ventas.model.entities.Store;
import com.system.ventas.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/system/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping
    public ResponseEntity<List<Store>> findAll() {
        List<Store> response = storeService.findAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Store> create(@Valid @RequestBody Store request) {
        Store response = storeService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> update(@Valid @RequestBody Store request) {
        Store response = storeService.update(request);
        return ResponseEntity.ok(response);
    }
}

