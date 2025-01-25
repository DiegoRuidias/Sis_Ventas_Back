package com.system.ventas.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenuUserDTO {
    private String label;
    private List<MenuItemsDTO> items;
}
