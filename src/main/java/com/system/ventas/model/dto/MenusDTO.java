package com.system.ventas.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenusDTO {
    
    private String key;
    private DataDTO data;
    private List<MenusDTO> children;

}
