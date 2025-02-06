package com.system.ventas.repository;

import com.system.ventas.model.entities.PettyCash;
import com.system.ventas.model.entities.Provider;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, String> {
    @Query("SELECT p FROM Provider p WHERE p.deletedAt IS NULL")
    List<Provider> findAllProviders();

    List<Provider> id(@Size(max = 36) String id);
}
