package com.system.ventas.service.Impl;

import com.system.ventas.exception.BusinessException;
import com.system.ventas.model.dto.PettyCashDTO;
import com.system.ventas.model.entities.PettyCash;
import com.system.ventas.repository.PettyCashRepository;
import com.system.ventas.service.PettyCashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    public PettyCash create(PettyCashDTO pettyCashDTO) {
        PettyCash pettyCash = new PettyCash();
        pettyCash.setReference(pettyCashDTO.getReference());
        pettyCash.setStartPetty(LocalDateTime.now());
        pettyCash.setTotalInit(pettyCashDTO.getTotalInit());
        pettyCash.setTotalCash(BigDecimal.valueOf(0.0));
        pettyCash.setTotalPos(BigDecimal.valueOf(0.0));
        pettyCash.setTotalBim(BigDecimal.valueOf(0.0));
        pettyCash.setIsActive(true);
        return pettyCashRepository.save(pettyCash);
    }

    @Transactional
    @Override
    public void endPetty(Integer id) {
        PettyCash entity = pettyCashRepository.findById(id)
                .orElseThrow(() -> new BusinessException("No se encontro la caja seleccionada"));
        entity.setEndPetty(LocalDateTime.now());
        entity.setIsActive(false);
    }

    @Transactional
    @Override
    public void deletePetty(Integer id) {
        PettyCash entity = pettyCashRepository.findById(id)
                .orElseThrow(()-> new BusinessException("No se encontro la caja seleccionada"));
        entity.setDeletedAt(LocalDateTime.now());
    }


}
