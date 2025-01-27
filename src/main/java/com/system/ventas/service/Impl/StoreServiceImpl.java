package com.system.ventas.service.Impl;

import com.system.ventas.exception.BusinessException;
import com.system.ventas.model.entities.Store;
import com.system.ventas.repository.StoreRepository;
import com.system.ventas.service.StoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<Store> findAll(){
        return storeRepository.findAll();
    }

    @Transactional
    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @Transactional
    @Override
    public Store update(Store store) {
        Store entity = storeRepository.findById(store.getId())
                .orElseThrow(() -> new BusinessException("El Id de la categoria no existe"));
        entity.setName(store.getName());
        entity.setDescription(store.getDescription());
        entity.setRuc(store.getRuc());
        return entity;
    }

}
