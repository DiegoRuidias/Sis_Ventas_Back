package com.system.ventas.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull(message = "El campo de codigo no debe ser nulo")
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @NotNull(message = "El campo metodo no debe ser nulo")
    @Column(name = "method", nullable = false)
    private String method;

    @NotNull(message = "El tipo de documento no debe ser nulo")
    @Column(name = "type_document", nullable = false)
    private String typeDocument;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pettycash_id", nullable = false)
    private PettyCash pettycash;

    @NotNull(message = "El total de las compras no debe ser nulo")
    @Column(name = "total_com", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCom;

    @Column(name = "total_igv", precision = 10, scale = 2)
    private BigDecimal totalIgv;

    @NotNull(message = "El total de las ventas no debe ser nulo")
    @Column(name = "total_vent", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalVent;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
