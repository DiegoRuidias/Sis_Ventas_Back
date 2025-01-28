package com.system.ventas.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PettyCashDTO {
    private String reference;
    private BigDecimal totalInit;
    private Integer userId;
}
