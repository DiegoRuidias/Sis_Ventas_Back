package com.system.ventas.service;

import com.system.ventas.model.entities.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> findAll();
    Provider save(Provider provider);
    Provider update(Provider provider);
}
