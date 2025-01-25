package com.system.ventas.controller.security;

import com.system.ventas.model.entities.Roles;
import com.system.ventas.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/security/roles")
public class RolesController {

  	@Autowired
	private RolesService rolesService;
    
    @PostMapping
	public ResponseEntity<Roles> create(@Validated @RequestBody Roles request) {
		Roles response = rolesService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Roles> update(@Validated @RequestBody Roles request) {
		Roles response = rolesService.update(request);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{id}/{isActive}")
	public ResponseEntity<Boolean> updateByIsActive(@PathVariable String id, @PathVariable Boolean isActive ) {
		rolesService.updateIsActiveById(isActive , id);
		return ResponseEntity.ok(true);
	}

    @GetMapping
	public ResponseEntity<List<Roles>> findAll() {
		List<Roles> response = rolesService.findAll();
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Boolean> updateDeleteAt(@PathVariable String id) {
		rolesService.deletedById(id);
		return ResponseEntity.ok(true);
	}

}
