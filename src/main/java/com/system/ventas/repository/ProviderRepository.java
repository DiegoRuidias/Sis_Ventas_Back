package com.system.ventas.repository;

import com.system.ventas.model.entities.Provider;
import org.springframework.data.repository.CrudRepository;

public interface ProviderRepository extends CrudRepository<Provider, String> {
}
