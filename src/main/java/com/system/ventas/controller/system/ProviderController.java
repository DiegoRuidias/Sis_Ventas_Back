package com.system.ventas.controller.system;

import com.system.ventas.model.entities.Provider;
import com.system.ventas.model.entities.Store;
import com.system.ventas.service.ProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/system/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @GetMapping
    public ResponseEntity<List<Provider>> findAll(){
        List<Provider> response = providerService.findAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Provider> create(@Valid @RequestBody Provider request){
        Provider response = providerService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Provider> update(@Valid @RequestBody Provider request) {
        Provider response = providerService.update(request);
        return ResponseEntity.ok(response);
    }
}
