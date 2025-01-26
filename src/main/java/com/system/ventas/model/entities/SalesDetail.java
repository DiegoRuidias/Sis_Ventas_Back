package com.system.ventas.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "sales_detail")
public class SalesDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "El campo cantidad no debe ser nulo")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull(message = "El precio de venta no debe ser nulo")
    @Column(name = "price_vent", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceVent;

    @NotNull(message = "El precio de compra no debe ser nulo")
    @Column(name = "price_com", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceCom;

    @NotNull(message = "El total de compras no debe ser nulo")
    @Column(name = "total_com", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCom;

    @Column(name = "total_igv", precision = 10, scale = 2)
    private BigDecimal totalIgv;

    @NotNull(message = "El total de ventas no debe ser nulo")
    @Column(name = "total_vent", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalVent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products product;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sales_id", nullable = false)
    private Sales sales;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
