package org.example.shoppingcartfinal.repository;


import org.example.shoppingcartfinal.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
 UserEntity findByUserId(String userId);

}
