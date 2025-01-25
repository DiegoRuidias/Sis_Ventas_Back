package com.system.ventas.service.Impl;

import com.system.ventas.model.entities.Roles;
import com.system.ventas.repository.RolesRepository;
import com.system.ventas.repository.UserRolesRepository;
import com.system.ventas.service.RolesService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("roleService")
public class RolesServiceImpl implements RolesService{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;

    @Override
	@Transactional
    public Roles create(Roles roles){
        Integer max = rolesRepository.findMaxSort() + 1;
        roles.setSort(max);
        entityManager.persist(roles);
        return roles;
    }

    @Override
    @Transactional
	public Roles update(Roles roles) {
        Roles entity = rolesRepository.getReferenceById(roles.getId());
        entity.setCode(roles.getCode());
        entity.setIsActive(roles.getIsActive());
        entity.setName(roles.getName());
        entity.setDescription(roles.getDescription());
		return entity;
	}

    @Override
    public List<Roles> findAll(){
        List<Roles> roles = rolesRepository.findAllRoles();
        return roles;
    }

    @Transactional
    @Override
    public void deletedById(String id) {
        LocalDateTime dato = LocalDateTime.now();
        userRolesRepository.deactivateRole(id);
        rolesRepository.updateDeletedAt(id, dato);
    }

    @Transactional
    @Override
    public void updateIsActiveById(Boolean isActive, String id){
        rolesRepository.updateIsActiveById(isActive, id);
    }

}
