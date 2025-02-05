package com.system.ventas.controller.system;

import com.system.ventas.model.dto.PettyCashDTO;
import com.system.ventas.model.entities.PettyCash;
import com.system.ventas.service.PettyCashService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/active/{userId}")
    public ResponseEntity<Boolean> activeUser(@PathVariable Integer userId){
         Boolean response = pettyCashService.activeById(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PettyCash> create(@Valid @RequestBody PettyCashDTO request){
        PettyCash response = pettyCashService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/end")
    public ResponseEntity<Boolean> endPetty(@PathVariable Integer id){
        pettyCashService.endPetty(id);
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        pettyCashService.deletePetty(id);
        return ResponseEntity.ok(true);
    }

}
