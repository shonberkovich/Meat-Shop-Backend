package org.example.shoppingcartfinal.repository;

import org.example.shoppingcartfinal.entities.ProductEntity;
import org.example.shoppingcartfinal.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
