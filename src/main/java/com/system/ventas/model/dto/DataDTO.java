package com.system.ventas.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataDTO {

    private String id;
    private String icon;
    private String label;
    private String routerLink;
    private Integer order;

}
