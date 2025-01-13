package org.example.shoppingcartfinal.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ShoppingCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ProductEntity> products;

    private double totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
        updateTotalPrice();

    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void addProductToShoppingCart(ProductEntity product){
        this.products.add(product);
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        totalPrice = products.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                '}';
    }
}