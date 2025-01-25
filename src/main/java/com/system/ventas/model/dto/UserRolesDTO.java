package com.system.ventas.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserRolesDTO {
    private String id;
    private int userId;
    private String roleId;
    private String roleName;
    private String roleDescription;
    private Boolean isActive;
    private Integer typePerson;
    private LocalDateTime createdAt;
}
