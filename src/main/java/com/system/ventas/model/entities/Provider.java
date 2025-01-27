package com.system.ventas.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "provider")
public class Provider {

    @Id
    @Size(max = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Size(max = 60)
    @NotNull(message = "El campo de nombre no debe ser nulo")
    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 1)
    @NotNull(message = "El tipo de documento no debe ser nulo")
    @Column(name = "document_type", nullable = false, length = 1)
    private String documentType;

    @Size(max = 20)
    @NotNull(message = "El número de documento no debe ser nulo")
    @Column(name = "document_number", nullable = false, length = 20)
    private String documentNumber;

    @NotNull(message = "El indicador de activo o inactivo no debe ser nulo")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
