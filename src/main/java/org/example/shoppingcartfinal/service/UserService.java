package org.example.shoppingcartfinal.service;

import jakarta.transaction.Transactional;
import org.example.shoppingcartfinal.entities.ProductEntity;
import org.example.shoppingcartfinal.entities.ShoppingCartEntity;
import org.example.shoppingcartfinal.entities.UserEntity;
import org.example.shoppingcartfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserEntity createUser(String userId) {
        ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
        shoppingCart.setProducts(List.of());
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        user.setShoppingCart(shoppingCart);
        return userRepository.save(user);
    }


    @Transactional
    public String addProductToUserCart(String userId, ProductEntity product) {
        UserEntity user = userRepository.findByUserId(userId);
        ShoppingCartEntity shoppingCart = user.getShoppingCart();

        List<ProductEntity> productEntities = shoppingCart.getProducts();
        Optional<ProductEntity> optionalProduct = productEntities.stream()
                .filter(p -> p.getName().equalsIgnoreCase(product.getName()))
                .findFirst();
        if (optionalProduct.isPresent()) {
            ProductEntity existingProduct = optionalProduct.get();
            existingProduct.setQuantity(product.getQuantity());
            shoppingCart.setProducts(productEntities);
            userRepository.save(user);
            return "Product updated successfully";
        } else {
            shoppingCart.addProductToShoppingCart(product);
            userRepository.save(user);
        }
        return "Product added successfully";
    }


    @Transactional
    public String removeProductFromUserCart(String userId, String productName) {

        UserEntity user = userRepository.findByUserId(userId);

        ShoppingCartEntity shoppingCart = user.getShoppingCart();
        List<ProductEntity> productEntities = shoppingCart.getProducts();

        Optional<ProductEntity> optionalProduct = productEntities.stream()
                .filter(p -> p.getName().equalsIgnoreCase(productName))
                .findFirst();

        if (optionalProduct.isPresent()) {
            productEntities.remove(optionalProduct.get());
            shoppingCart.setProducts(productEntities);
        }
        userRepository.save(user);
        return "Product removed successfully";
    }

    public List<ProductEntity> getUserProducts(String userId) {
        UserEntity user = userRepository.findByUserId(userId);


        ShoppingCartEntity cart = user.getShoppingCart();
        return cart.getProducts();
    }


//    public ShoppingCartEntity getUserCart(String userId) {
//
//    }

}
