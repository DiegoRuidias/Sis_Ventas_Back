package com.system.ventas.controller.system;

import com.system.ventas.model.dto.SalesDTO;
import com.system.ventas.model.entities.Sales;
import com.system.ventas.service.Impl.SalesServiceImpl;
import com.system.ventas.service.SalesService;
import jakarta.validation.Valid;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/system/sales")
public class SalesController {
    private final SalesService salesService;

    public SalesController(SalesServiceImpl salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@Valid @RequestBody SalesDTO request) {
        salesService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping
    public ResponseEntity<List<Sales>> findAll(
            @RequestParam LocalDateTime date ) {
        List<Sales> response = salesService.findByDate(date);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paged")
    public ResponseEntity<PagedModel<Sales>> findPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam LocalDateTime date) {
        PagedModel<Sales> response = salesService.findByDatePaged(page,size,date) ;
        return ResponseEntity.ok(response);
    }
}
