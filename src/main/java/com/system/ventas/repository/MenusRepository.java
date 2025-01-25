package com.system.ventas.repository;


import com.system.ventas.model.entities.Menus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenusRepository extends JpaRepository<Menus,String>{
    List<Menus> findAllByOrderBySortAsc();

    @Query("SELECT m FROM Menus m WHERE (m.typePerson=:typePerson OR m.typePerson = 6) ORDER BY m.sort ASC")
    List<Menus> findAllByTypePersonOrderBySortAsc(Integer typePerson);

}
