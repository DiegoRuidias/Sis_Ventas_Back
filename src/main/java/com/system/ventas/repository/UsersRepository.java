package com.system.ventas.repository;

import com.system.ventas.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Integer>{
    @Modifying
    @Query("UPDATE Users r SET r.isActive = :isActive WHERE r.id = :id")
    void updateIsActiveById(Boolean isActive, String id);

    List<Users> findAllByUsername(String username);

    Users findByUsername(String username);

}