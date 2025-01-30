package com.system.ventas.service.Impl;

import com.system.ventas.exception.BusinessException;
import com.system.ventas.model.dto.ProductDTO;
import com.system.ventas.model.dto.ReorderDTO;
import com.system.ventas.model.dto.ReorderIntDTO;
import com.system.ventas.model.entities.Categories;
import com.system.ventas.model.entities.Products;
import com.system.ventas.model.entities.Store;
import com.system.ventas.repository.CategoriesRepository;
import com.system.ventas.repository.ProductsRepository;
import com.system.ventas.repository.StoreRepository;
import com.system.ventas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("productoService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ProductsRepository productsRepository;


    @Transactional
    @Override
    public void create(ProductDTO product) {
        Store store = storeRepository.findById(product.getStoreId())
                .orElseThrow(() -> new BusinessException("El almacén ingresado no existe "));
        Categories categories = categoriesRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new BusinessException("La categoria ingresada no existe ") );

        Products products = new Products();
        products.setBarcode(product.getBarcode());
        products.setDescription(product.getDescription());
        products.setName(product.getName());
        products.setIsBox(product.getIsBox());
        products.setIsActive(true);
        products.setStock(0);
        products.setPriceCom(BigDecimal.valueOf(0));
        products.setPriceVent(BigDecimal.valueOf(0));
        products.setMeasure(product.getMeasure());
        products.setIsIgv(true);
        products.setSort(1);
        products.setMeasureBox(BigDecimal.valueOf(0));
        products.setStore(store);
        products.setCategory(categories);
        products.setBoxes(0);
        productsRepository.save(products);

    }

    @Transactional
    @Override
    public void reorder(Set<ReorderIntDTO> reorder) {
        reorder.forEach(item -> {
            Optional<Products> entity = productsRepository.findById(item.id());
            entity.ifPresent(products -> products.setSort(item.sort()));
        });
    }

    @Override
    public List<Products> findByStoreId(String storeId) {
        return productsRepository.findProductsByStoreId(storeId);
    }
}
