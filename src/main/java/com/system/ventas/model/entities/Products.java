package com.system.ventas.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import org.springframework.data.annotation.LastModifiedDate;
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
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "barcode")
    private String barcode;

    @NotNull(message = "El campo de orden no debe ser nulo")
    @Column(name = "sort", nullable = false)
    private Integer sort;

    @Size(max = 60)
    @NotNull(message = "El campo de nombre no debe ser nulo")
    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @Size(max = 50)
    @Column(name = "measure", length = 50)
    private String measure;

    @NotNull(message = "El campo stock no debe ser nulo")
    @Column(name = "stock", nullable = false)
    private BigDecimal stock;

    @NotNull(message = "El precio de compra no debe ser nulo")
    @Column(name = "price_com", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceCom;

    @NotNull(message = "El precio de venta no debe ser nulo")
    @Column(name = "price_vent", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceVent;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "is_igv", nullable = false)
    private Boolean isIgv = true;

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
    @Size(max = 600)
    @Column(name = "search_text", length = 600)
    private String searchText;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories category;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonIgnore
    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
