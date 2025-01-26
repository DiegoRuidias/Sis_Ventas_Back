package com.system.ventas.service.Impl;

import com.system.ventas.exception.BusinessException;
import com.system.ventas.model.entities.Categories;
import com.system.ventas.repository.CategoriesRepository;
import com.system.ventas.service.CategoriesService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoriesService")
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    @Transactional
    @Override
    public Categories save(Categories categories) {
        return categoriesRepository.save(categories);
    }

    @Transactional
    @Override
    public Categories update(Categories categories) {
        if(!(StringUtils.isNoneBlank(categories.getId()) && categoriesRepository.existsById(categories.getId()))){
            throw new BusinessException ("El Id de la categoria no existe");
        }
        return categoriesRepository.save(categories);
    }
}
