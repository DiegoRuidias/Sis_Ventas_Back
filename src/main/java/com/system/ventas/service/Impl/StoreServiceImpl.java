package com.system.ventas.service.Impl;

import com.system.ventas.exception.BusinessException;
import com.system.ventas.model.dto.ReorderDTO;
import com.system.ventas.model.entities.Store;
import com.system.ventas.repository.StoreRepository;
import com.system.ventas.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<Store> findAll(){
        return storeRepository.findAll(Sort.by("sort"));
    }

    @Transactional
    @Override
    public Store save(Store store) {
        store.setSort(1);
        store.setIsActive(true);
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

    @Transactional
    @Override
    public void reorder(Set<ReorderDTO> reorder) {
        reorder.forEach(item -> {
            Optional<Store> entity = storeRepository.findById(item.id());
            entity.ifPresent(store -> store.setSort(item.sort()));
        });
    }

}
