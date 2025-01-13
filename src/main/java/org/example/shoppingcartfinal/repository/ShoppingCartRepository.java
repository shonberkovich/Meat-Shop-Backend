package org.example.shoppingcartfinal.repository;

import org.example.shoppingcartfinal.entities.ShoppingCartEntity;
import org.example.shoppingcartfinal.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {
}
