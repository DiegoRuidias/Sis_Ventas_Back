package com.system.ventas.service.Impl;

import com.system.ventas.exception.BusinessException;
import com.system.ventas.model.entities.PettyCash;
import com.system.ventas.model.entities.Provider;
import com.system.ventas.repository.ProviderRepository;
import com.system.ventas.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public List<Provider> findAll() {
        return providerRepository.findAllProviders();
    }

    @Transactional
    @Override
    public Provider save(Provider provider) {
        provider.setIsActive(true);
        return providerRepository.save(provider);
    }

    @Transactional
    @Override
    public Provider update(Provider provider) {
        Provider entity = providerRepository.findById(provider.getId())
                .orElseThrow(() -> new BusinessException("El Id de la categoria no existe"));
        entity.setName(provider.getName());
        entity.setDescription(provider.getDescription());
        entity.setDocumentType(provider.getDocumentType());
        entity.setDocumentNumber(provider.getDocumentNumber());
        return entity;
    }

    @Transactional
    @Override
    public void deleteProvider(String id) {
        Provider entity = providerRepository.findById(id)
                .orElseThrow(()-> new BusinessException("No se encontro el proveedor seleccionado"));
        entity.setDeletedAt(LocalDateTime.now());
    }
}
