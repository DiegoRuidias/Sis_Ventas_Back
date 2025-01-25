package com.system.ventas.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "Menus")
public class Menus {
    
    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "is_active",nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private Integer sort;

    @Column(nullable = false)
    private Integer position;

    @Column(nullable = false, length = 100, unique = true)
    private String label;

    @Column(nullable = false, length = 20)
    private String icon;

    @Column(name = "router_link",length = 50)
    private String routerLink;

    @NotNull
    @Column(name = "type_person", nullable = false)
    private Integer typePerson;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Menus parent;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
