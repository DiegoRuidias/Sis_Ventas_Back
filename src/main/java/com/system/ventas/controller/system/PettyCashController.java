package com.system.ventas.controller.system;

import com.system.ventas.model.entities.PettyCash;
import com.system.ventas.service.PettyCashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/system/petty-cash")
public class PettyCashController {
    @Autowired
    private PettyCashService pettyCashService;

    @GetMapping
    public ResponseEntity<List<PettyCash>> findAll(){
        List<PettyCash> response = pettyCashService.findAll();
        return ResponseEntity.ok(response);
    }

}
