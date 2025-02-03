package com.system.ventas.service.Impl;

import com.system.ventas.model.entities.Customers;
import com.system.ventas.repository.CustomersRepository;
import com.system.ventas.service.CustormersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CustomersService")
public class CustomersServiceImpl implements CustormersService {

    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public List<Customers> findAll() {
        return customersRepository.findAll();
    }
}
