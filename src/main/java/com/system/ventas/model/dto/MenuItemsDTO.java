package com.system.ventas.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenuItemsDTO {
    private String label;
    private String icon;
    private String routerLink;
    private List<MenuItemsDTO> items;
}
