package com.system.ventas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDTO {
    private String barcode;
    private String name;
    private String description;
    private Boolean isActive;
    private Boolean isBox;
    private String categoryId;
    private String measure;
    private String storeId;
    private int id;
}
