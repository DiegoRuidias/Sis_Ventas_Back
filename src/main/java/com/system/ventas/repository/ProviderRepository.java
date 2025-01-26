package com.system.ventas.repository;

import com.system.ventas.model.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, String> {

}
