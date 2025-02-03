package com.system.ventas.service;

import com.system.ventas.model.dto.ProductDTO;
import com.system.ventas.model.dto.ReorderIntDTO;
import com.system.ventas.model.entities.Products;
import org.springframework.data.web.PagedModel;

import java.util.List;
import java.util.Set;

public interface ProductService {
    void create(ProductDTO product);
    void reorder(Set<ReorderIntDTO> reorder);
    List<Products> findByStoreId (String storeId);
    PagedModel<Products> findByStoreIdPaged (int page, int size, String storeId, String searchText);
 }
