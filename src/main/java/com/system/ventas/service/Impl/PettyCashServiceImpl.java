package com.system.ventas.service.Impl;

import com.system.ventas.model.entities.PettyCash;
import com.system.ventas.repository.PettyCashRepository;
import com.system.ventas.service.PettyCashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("PettyCashSevice")
public class PettyCashServiceImpl implements PettyCashService {
    @Autowired
    private PettyCashRepository pettyCashRepository;

    @Override
    public List<PettyCash> findAll() {
        return pettyCashRepository.findAllPettyCash();
    }

    @Transactional
    @Override
    public PettyCash create(PettyCash pettyCash) {
        return null;
    }
}
