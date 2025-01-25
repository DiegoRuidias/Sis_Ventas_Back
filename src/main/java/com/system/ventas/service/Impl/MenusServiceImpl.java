package com.system.ventas.service.Impl;

import com.system.ventas.model.dto.DataDTO;
import com.system.ventas.model.dto.MenuItemsDTO;
import com.system.ventas.model.dto.MenuUserDTO;
import com.system.ventas.model.dto.MenusDTO;
import com.system.ventas.model.entities.Menus;
import com.system.ventas.repository.MenusRepository;
import com.system.ventas.service.MenusService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service("menusService")
public class MenusServiceImpl implements MenusService{
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MenusRepository menusRepository;

	@Override
	@Transactional
    public Menus create(Menus menus){

		menus.setCreatedAt(LocalDateTime.now());
		entityManager.persist(menus);
		return menus;
  }

  @Override
  public List<MenusDTO> findAll() {
    List<Menus> allMenus = menusRepository.findAllByOrderBySortAsc();

    Map<String, List<Menus>> menuMap = allMenus.stream()
            .collect(Collectors.groupingBy(menu -> menu.getParent() != null ? menu.getParent().getId() : "root"));

    return buildMenuTree(menuMap, "root");
  }

  private List<MenusDTO> buildMenuTree(Map<String, List<Menus>> menuMap, String parentId) {
    List<MenusDTO> list = new ArrayList<>();

    List<Menus> menus = menuMap.getOrDefault(parentId, Collections.emptyList());
    for (Menus menu : menus) {
      DataDTO data = DataDTO.builder()
              .id(menu.getId())
              .label(menu.getLabel())
              .icon(menu.getIcon())
              .routerLink(menu.getRouterLink())
              .order(menu.getSort())
              .build();
      MenusDTO node = MenusDTO.builder()
              .key(menu.getId())
              .data(data)
              .children(buildMenuTree(menuMap, menu.getId()))
              .build();
      list.add(node);
    }
    return list;
  }

    @Override
    public List<MenuUserDTO> getMenuByTypePerson(Integer typePerson) {
        // Obtener todos los menús de la base de datos para el tipo de persona especificado
        List<Menus> allMenus = menusRepository.findAllByTypePersonOrderBySortAsc(typePerson);

        // Agrupamos los menús por posición (1 = padre, 2 = hijo, 3 = nieto)
        Map<Integer, List<Menus>> menuByPosition = allMenus.stream()
                .sorted(Comparator.comparingInt(Menus::getSort))  // Ordenamos por 'sort'
                .collect(Collectors.groupingBy(Menus::getPosition));  // Agrupamos por 'position'

        // Mapeamos los menús principales (nivel 1)
        return menuByPosition.getOrDefault(1, Collections.emptyList()).stream()
                .map(menu -> mapMenuToDTO(menu, menuByPosition))
                .collect(Collectors.toList());
    }

    // Mapea un menú de primer nivel (nivel 1) y busca sus hijos
    private MenuUserDTO mapMenuToDTO(Menus menu, Map<Integer, List<Menus>> menuByPosition) {
        List<MenuItemsDTO> items = new ArrayList<>();

        // Buscar menús de nivel 2 (hijos)
        List<MenuItemsDTO> childMenus = menuByPosition.getOrDefault(2, Collections.emptyList()).stream()
                .filter(subMenu -> subMenu.getParent() != null && subMenu.getParent().getId().equals(menu.getId()))
                .map(subMenu -> mapMenuItemsToDTO(subMenu, menuByPosition))
                .collect(Collectors.toList());

        if (!childMenus.isEmpty()) {
            items.addAll(childMenus);
        }

        return MenuUserDTO.builder()
                .label(menu.getLabel())
                .items(items)
                .build();
    }

    // Mapea los submenús (nivel 2) y busca los nietos (nivel 3)
    private MenuItemsDTO mapMenuItemsToDTO(Menus menu, Map<Integer, List<Menus>> menuByPosition) {
        List<MenuItemsDTO> subItems = new ArrayList<>();

        // Buscar menús de nivel 3 (nietos)
        List<MenuItemsDTO> grandChildMenus = menuByPosition.getOrDefault(3, Collections.emptyList()).stream()
                .filter(subMenu -> subMenu.getParent() != null && subMenu.getParent().getId().equals(menu.getId()))
                .map(subMenu -> mapMenuItemsToDTO(subMenu, menuByPosition))
                .collect(Collectors.toList());

        if (!grandChildMenus.isEmpty()) {
            subItems.addAll(grandChildMenus);
        }

        return MenuItemsDTO.builder()
                .label(menu.getLabel())
                .icon(menu.getIcon())
                .routerLink(menu.getRouterLink())
                .items(subItems.isEmpty() ? null : subItems)  // Si no hay subitems, asignamos null
                .build();
    }






}
  
