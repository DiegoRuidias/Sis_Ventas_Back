package com.system.ventas.service.Impl;

import com.system.ventas.exception.BusinessException;
import com.system.ventas.model.dto.ProductEntriesDTO;
import com.system.ventas.model.entities.ProductEntries;
import com.system.ventas.model.entities.Products;
import com.system.ventas.model.entities.Provider;
import com.system.ventas.model.entities.Store;
import com.system.ventas.repository.ProductEntriesRepository;
import com.system.ventas.repository.ProductsRepository;
import com.system.ventas.repository.ProviderRepository;
import com.system.ventas.repository.StoreRepository;
import com.system.ventas.service.ProductEntriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("requestService")
public class ProductEntriesServiceImpl implements ProductEntriesService {

    @Autowired
    private ProductEntriesRepository requestRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private ProductEntriesRepository productEntriesRepository;
    @Autowired
    private StoreRepository storeRepository;

    @Transactional
    @Override
    public void create(ProductEntriesDTO request) {
        Products products = productsRepository.findById(request.getProductId())
                .orElseThrow(() -> new BusinessException("El producto ingresado no existe."));
        products.setStock(products.getStock().add(request.getStock()));
        products.setPriceVent(request.getPriceVent());
        products.setPriceCom(request.getPriceCom());

        Provider provider = providerRepository.findById(request.getProviderId())
                .orElseThrow(() -> new BusinessException("El proovedor ingresado no existe."));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new BusinessException("El store ingresado no existe."));

        ProductEntries response = new ProductEntries();
        response.setCalculate(true);
        response.setProduct(products);
        response.setProvider(provider);
        response.setIsBox(products.getIsBox());
        response.setStock(request.getStock());
        response.setPriceVent(request.getPriceVent());
        response.setPriceCom(request.getPriceCom());
        response.setDescription("Producto - " +  products.getName() + (request.getDescription() == null ? "" : " - " + request.getDescription()));
        response.setDate(LocalDateTime.now());
        response.setIsIgv(request.getIsIgv());
        response.setTotalCom(request.getTotalCom());
        response.setMeasureBox(request.getMeasureBox());
        response.setPriceBox(request.getPriceBox());
        response.setBoxes(request.getBoxes());
        response.setMeasure(request.getMeasure());
        response.setStore(store);
        if(products.getIsBox()){
            products.setMeasureBox(request.getMeasureBox());
            products.setBoxes((products.getBoxes() == null ? 0 : products.getBoxes())  + request.getBoxes());
            products.setPriceBox(request.getPriceBox());
        }
        requestRepository.save(response);
    }

    @Override
    public List<ProductEntries> findByDateAndStore(LocalDateTime date, String storeId) {
        LocalDateTime endOfDay = date.plusDays(1);
        return productEntriesRepository.findByStoreAndDate(date, endOfDay, storeId);
    }
}
