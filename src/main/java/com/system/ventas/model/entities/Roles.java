package com.system.ventas.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Roles")
public class Roles {
    
    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "is_active",nullable = false)
    private Boolean isActive;

    @Column(nullable = false, length = 50, unique = true)
    private String code;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(length = 100)
    private String description;

    @Column(nullable = false)
    private Integer sort;

    @NotNull
    @Column(name = "type_person", nullable = false)
    private Integer typePerson;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

//    @OneToMany(mappedBy = "roles")
//    private List<RolePermissions> rolePermissions = new ArrayList<>();
}

