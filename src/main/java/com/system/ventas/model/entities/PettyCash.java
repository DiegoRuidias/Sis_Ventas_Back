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

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Petty_Cash")
public class PettyCash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 10)
    @NotNull
    @Column(name = "reference", nullable = false, length = 10)
    private String reference;

    @NotNull
    @Column(name = "start_petty", nullable = false)
    private LocalDateTime startPetty;

    @Column(name = "end_petty", nullable = false)
    private LocalDateTime endPetty;

    @NotNull
    @Column(name = "total_init", nullable = false)
    private Double totalInit;

    @Column(name = "total_cash")
    private Double totalCash;

    @Column(name = "total_pos")
    private Double totalPos;

    @Column(name = "total_bim")
    private Double totalBim;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
