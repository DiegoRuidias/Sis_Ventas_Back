package com.system.ventas.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class SalesDTO {
    @NotNull(message = "El campo de codigo no debe ser nulo")
    private String code;

    @NotNull(message = "El campo metodo no debe ser nulo")
    private String method;

    @NotNull(message = "El tipo de documento no debe ser nulo")
    private String typeDocument;

    private int customerId;
    private int userId;
    private BigDecimal totalIgv;
    @NotNull(message = "El total de las ventas no debe ser nulo")
    private BigDecimal totalVent;
    private List<SalesDetailDTO> detailsSales;
}
