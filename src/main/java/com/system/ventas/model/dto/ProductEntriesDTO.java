package com.system.ventas.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductEntriesDTO {
    private Integer id;
    private String description;
    private String measure;
    private Integer stock;
    private BigDecimal totalCom;
    private BigDecimal priceCom;
    private BigDecimal priceVent;
    private Boolean isBox;
    private Integer boxes;
    private BigDecimal measureBox;
    private BigDecimal priceBox;
    private Boolean isIgv;
    private Integer productId;
    private String  providerId;
    private String  storeId;
}
