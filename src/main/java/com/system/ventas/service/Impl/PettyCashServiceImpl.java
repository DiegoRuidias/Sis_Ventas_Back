package com.system.ventas.service.Impl;

import com.system.ventas.exception.BusinessException;
import com.system.ventas.model.dto.PettyCashDTO;
import com.system.ventas.model.entities.PettyCash;
import com.system.ventas.model.entities.Users;
import com.system.ventas.repository.PettyCashRepository;
import com.system.ventas.repository.UsersRepository;
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
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<PettyCash> findAll() {
        return pettyCashRepository.findAllPettyCash();
    }

    @Transactional
    @Override
    public PettyCash create(PettyCashDTO pettyCashDTO) {
        Users user = usersRepository.findById(pettyCashDTO.getUserId())
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));
        PettyCash pettyCash = new PettyCash();
        pettyCash.setReference(pettyCashDTO.getReference());
        pettyCash.setStartPetty(LocalDateTime.now());
        pettyCash.setTotalInit(pettyCashDTO.getTotalInit());
        pettyCash.setTotalCash(BigDecimal.valueOf(0.0));
        pettyCash.setTotalPos(BigDecimal.valueOf(0.0));
        pettyCash.setTotalBim(BigDecimal.valueOf(0.0));
        pettyCash.setUser(user);
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
        pettyCashRepository.delete(entity);
    }

    @Override
    public Boolean activeById(Integer userId) {
        int response = pettyCashRepository.findPettyCashActive(userId);
        return response != 0;
    }

    @Transactional
    @Override
    public void update(Integer userId, BigDecimal totalPos, BigDecimal totalCash) {
        int response = pettyCashRepository.updateAmounts(userId, totalPos, totalCash);
        if(response == 0){
            throw new BusinessException("No se encontro la caja seleccionada");
        }
    }


}
