package org.example.shoppingcartfinal.controller;

import org.example.shoppingcartfinal.entities.ProductEntity;
import org.example.shoppingcartfinal.entities.UserEntity;
import org.example.shoppingcartfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shop/")
public class userController {
    @Autowired
    private UserService userService;


    @PostMapping("/add-user/{userId}")
    public void createUser(@PathVariable String userId) {
        userService.createUser(userId);
    }


    @PostMapping("/add-Product-To-User/{userId}")
    public String addProductToUser(@PathVariable String userId, @RequestBody ProductEntity product) {
        return userService.addProductToUserCart(userId, product);
    }

    @PostMapping("/remove-Product-From-User/{userId}/{productName}")
    public String removeProductFromUser(@PathVariable String userId, @PathVariable String productName) {
        return userService.removeProductFromUserCart(userId, productName);
    }

    @GetMapping("/get-user-pruducts/{userId}")
    public List<ProductEntity> getUserProducts(@PathVariable String userId) {
        return userService.getUserProducts(userId);

    }

}
