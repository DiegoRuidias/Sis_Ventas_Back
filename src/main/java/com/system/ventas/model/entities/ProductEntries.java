package com.system.ventas.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","deletedAt","createdAt"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product_entries")
public class ProductEntries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @Size(max = 50)
    @Column(name = "measure", length = 50)
    private String measure;

    @NotNull(message = "El stock no debe ser nulo")
    @Column(name = "stock", nullable = false)
    private BigDecimal stock;

    @NotNull(message = "El total de compras no debe ser nulo")
    @Column(name = "total_com", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCom;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Column(name = "calculate", nullable = false)
    private Boolean calculate = false;

    @NotNull(message = "El precio de compra no debe ser nulo")
    @Column(name = "price_com", nullable = false, precision = 5, scale = 2)
    private BigDecimal priceCom;

    @NotNull(message = "El precio de venta no debe ser nulo")
    @Column(name = "price_vent", nullable = false, precision = 5, scale = 2)
    private BigDecimal priceVent;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "is_igv", nullable = false)
    private Boolean isIgv = false;

    @Column(name = "boxes")
    private Integer boxes;

    @Column(name = "measure_box", precision = 10, scale = 2)
    private BigDecimal measureBox;

    @Column(name = "price_box", precision = 5, scale = 2)
    private BigDecimal priceBox;

    @NotNull
    @Column(name = "is_box", nullable = false)
    private Boolean isBox = false;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


}
