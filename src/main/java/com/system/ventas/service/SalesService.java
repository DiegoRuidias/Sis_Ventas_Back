package com.system.ventas.service;

import com.system.ventas.model.dto.SalesDTO;
import com.system.ventas.model.entities.Sales;
import org.springframework.data.web.PagedModel;

import java.time.LocalDateTime;
import java.util.List;

public interface SalesService {
    void create (SalesDTO sales);
    List<Sales> findByDate(LocalDateTime date);
    PagedModel<Sales> findByDatePaged(int page, int size,LocalDateTime date);
}
