package com.system.ventas.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UsersDTO {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String password;
    private Boolean isActive;
    private LocalDateTime createdAt;

}
