package com.system.ventas.repository;

import com.system.ventas.model.entities.Store;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<Store, String> {
}
