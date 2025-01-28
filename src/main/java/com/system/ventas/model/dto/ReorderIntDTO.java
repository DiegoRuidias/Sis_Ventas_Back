package com.system.ventas.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReoderIntDTO (
    @NotNull(message = "El id no puede estar en blanco") Integer id,
    @NotNull(message = "El orden es un dato requerido") Integer sort
    ) {};
