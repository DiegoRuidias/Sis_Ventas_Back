package com.system.ventas.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


public record ReorderDTO (
    @NotBlank(message = "El id no puede estar en blanco") String id,
    @NotNull(message = "El orden es un dato requerido") Integer sort
    ) {};