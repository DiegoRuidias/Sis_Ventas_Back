package com.system.ventas.service;

import com.system.ventas.model.entities.Customers;

import java.util.List;

public interface CustormersService {
    List<Customers> findAll();
    Customers create(Customers customers);
}
