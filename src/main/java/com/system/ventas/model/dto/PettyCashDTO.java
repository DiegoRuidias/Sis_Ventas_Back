package com.system.ventas.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PettyCashDTO {
    private String reference;
    private Double totalInit;
    private Integer userId;
}
