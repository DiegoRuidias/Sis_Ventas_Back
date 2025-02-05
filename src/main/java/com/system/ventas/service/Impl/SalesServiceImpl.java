package com.system.ventas.service.Impl;

import com.system.ventas.exception.BusinessException;
import com.system.ventas.model.dto.SalesDTO;
import com.system.ventas.model.entities.*;
import com.system.ventas.repository.*;
import com.system.ventas.service.PettyCashService;
import com.system.ventas.service.SalesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service("salesService")
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;
    private final ProductsRepository productsRepository;
    private final SalesDetailRepository salesDetailRepository;
    private final CustomersRepository customersRepository;
    private final UsersRepository usersRepository;
    private final PettyCashService pettyCashService;

    public SalesServiceImpl(SalesRepository salesRepository, ProductsRepository productsRepository, SalesDetailRepository salesDetailRepository, CustomersRepository customersRepository, UsersRepository usersRepository, PettyCashService pettyCashService) {
        this.salesRepository = salesRepository;
        this.productsRepository = productsRepository;
        this.salesDetailRepository = salesDetailRepository;
        this.customersRepository = customersRepository;
        this.usersRepository = usersRepository;
        this.pettyCashService = pettyCashService;
    }

    @Transactional
    @Override
    public void create(SalesDTO sales) {
        Customers customer = customersRepository.findById(sales.getCustomerId())
                .orElseThrow(() -> new BusinessException("El cliente no existe"));
        Users user = usersRepository.findById(sales.getUserId())
                .orElseThrow(() -> new BusinessException("El usuario no existe"));

        Sales entity = new Sales();
        entity.setId(UUID.randomUUID().toString());
        entity.setMethod(sales.getMethod());
        entity.setCode("sddsds");
        entity.setTypeDocument(sales.getTypeDocument());
        entity.setDate(LocalDateTime.now());
        entity.setTotalCom(BigDecimal.ZERO);
        entity.setTotalIgv(sales.getTotalIgv());
        entity.setTotalVent(sales.getTotalVent());
        Sales saveEntity = salesRepository.save(entity);
        saveEntity.setCustomer(customer);
        saveEntity.setUser(user);

        List<SalesDetail> salesDetails = new ArrayList<>();
        final BigDecimal[] totalCom = {BigDecimal.ZERO};
        sales.getDetailsSales().forEach(details -> {
            if(details.getIsBox()){
                int update = productsRepository.updateBox(details.getProductId(),details.getQuantity().intValue());
                if(update == 0) {
                    throw new BusinessException("No hay stock de " + details.getDescription());
                }
            } else {
                int update = productsRepository.updateStock(details.getProductId(),details.getQuantity());
                if(update == 0) {
                    throw new BusinessException("No hay stock de " + details.getDescription());
                }
            }
            Products products = productsRepository.findById(details.getProductId())
                    .orElseThrow(() -> new BusinessException("El producto no existe"));


            SalesDetail salesDetail = new SalesDetail();
            salesDetail.setIsBox(details.getIsBox());
                if(!details.getIsBox()){
                    salesDetail.setPriceCom(products.getPriceCom());
                    salesDetail.setTotalCom(products.getPriceCom().multiply(details.getQuantity()));
                    salesDetail.setMeasure(details.getMeasure());
                    salesDetail.setPriceVent(products.getPriceVent());
                } else {
                    salesDetail.setBoxes(details.getQuantity().intValue());
                    salesDetail.setMeasureBox(details.getMeasureBox());
                    salesDetail.setPriceCom(products.getPriceCom().multiply(details.getMeasureBox()));
                    salesDetail.setTotalCom(salesDetail.getPriceCom().multiply(details.getQuantity()));
                    salesDetail.setMeasure(details.getMeasure());
                    salesDetail.setPriceVent(products.getPriceBox());
                }
            salesDetail.setTotalVent(details.getAmount());
            salesDetail.setQuantity(details.getQuantity());
            salesDetail.setDescription(details.getDescription());
            salesDetail.setProduct(products);
            salesDetail.setSales(saveEntity);
            salesDetails.add(salesDetail);
            totalCom[0] = totalCom[0].add(salesDetail.getTotalCom());
        });
        if(Objects.equals(sales.getMethod(), "Efectivo")){
            pettyCashService.update(sales.getUserId(), BigDecimal.ZERO,sales.getTotalVent());
        }else {
            pettyCashService.update(sales.getUserId(), sales.getTotalVent(),BigDecimal.ZERO);
        }
        saveEntity.setTotalCom(totalCom[0]);
        salesDetailRepository.saveAll(salesDetails);
    }

    @Override
    public List<Sales> findByDate(LocalDateTime date) {
        LocalDateTime endOfDay = date.plusDays(1);
        return salesRepository.findAllByDate(date, endOfDay);
    }

    @Override
    public PagedModel<Sales> findByDatePaged(int page, int size,LocalDateTime date) {
        LocalDateTime endOfDay = date.plusDays(1);
        PageRequest pageable = PageRequest.of(page, size);
        Page<Sales> response = salesRepository.findPagedSales(pageable,date,endOfDay);
        return new PagedModel<>(response);
    }
}
