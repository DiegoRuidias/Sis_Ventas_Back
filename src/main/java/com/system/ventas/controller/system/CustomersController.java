package com.system.ventas.controller.system;

import com.system.ventas.model.entities.Customers;
import com.system.ventas.service.CustormersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/system/customers")
public class CustomersController {
    @Autowired
    private CustormersService custormersService;

    @GetMapping
    public ResponseEntity<List<Customers>> findAll(){
        List<Customers> response = custormersService.findAll();
        return ResponseEntity.ok(response);
    }
}
