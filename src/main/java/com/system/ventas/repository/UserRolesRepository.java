package com.system.ventas.repository;

import com.system.ventas.model.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles,String>{
    List<UserRoles> findAllByUserId(Integer userId);

    @Query("SELECT u FROM UserRoles u WHERE u.user.id = :userId AND u.role.id = :roleId AND u.role.deletedAt is null")
    UserRoles findByUserIdAndRoleId(Integer userId, String roleId);

    @Query("SELECT d FROM UserRoles d JOIN FETCH d.role r WHERE (d.user.id=:userId AND d.isActive = true AND r.isActive = true AND r.deletedAt IS NULL )")
    UserRoles findByUserId(Integer userId);

    @Modifying
    @Query("UPDATE UserRoles u SET u.isActive = false WHERE u.user.id =:userId")
    void updateIsActive(Integer userId);

    @Modifying
    @Query("UPDATE UserRoles u SET u.isActive = false WHERE u.role.id =:roleId")
    void deactivateRole (String roleId);

}
