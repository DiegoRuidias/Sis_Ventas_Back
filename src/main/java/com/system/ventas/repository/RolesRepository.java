package com.system.ventas.repository;

import com.system.ventas.model.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RolesRepository extends JpaRepository<Roles,String>{

    @Query("SELECT COALESCE(MAX(r.sort), 0) FROM Roles r")   
    Integer findMaxSort();

    @Query("SELECT r FROM Roles r WHERE r.deletedAt IS NULL ORDER BY r.sort ASC")
    List<Roles> findAllRoles();

    @Modifying
    @Query("UPDATE Roles r SET r.isActive = :isActive WHERE r.id = :id")
    void updateIsActiveById(Boolean isActive, String id);

    @Modifying
    @Query("UPDATE Roles r SET r.name=CONCAT(r.name, ' dx'), r.deletedAt = :deletedAt WHERE r.id = :id")
    void updateDeletedAt(String id, LocalDateTime deletedAt);

}
