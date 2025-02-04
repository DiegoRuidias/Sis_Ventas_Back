package com.system.ventas.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class SalesDetailDTO {

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    @NotNull(message = "La cantidad es obligatoria")
    private BigDecimal quantity;

    @NotNull(message = "El monto es obligatorio")
    private BigDecimal amount;

    @NotNull(message = "El ID del producto es obligatorio")
    private int productId;

    @NotNull(message = "El estado de edición es obligatorio")
    private Boolean isEdit;

    @NotBlank(message = "La unidad de medida es obligatoria")
    private String measure;

    @NotNull(message = "El precio es obligatorio")
    private Double price;

    @NotNull(message = "Debe indicar si es una caja")
    private Boolean isBox;

    @NotNull(message = "El precio inicial es obligatorio")
    private Double priceInit;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String name;

    @NotNull(message = "El stock inicial es obligatorio")
    private BigDecimal stockFirst;

    private BigDecimal measureBox;
    private String measureBoxSuf;
}
