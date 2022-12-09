package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Named @SessionScoped
public class CartController implements Serializable {

    private final static Logger log = Logger
            .getLogger(CartController.class.toString());

    @Getter
    private Set<Product> cart;

    private double sum;

    public void addToCart(Product product){
        if(cart == null){
            cart = new HashSet<>();
        }

        cart.add(product);

        log.info("COMPLETED: Added " + product.getName() + " to cart.");
    }

    public double getSum(){
        calculateSum();

        return sum;
    }

    public boolean hasProducts(){
        return cart != null && !cart.isEmpty();
    }

    public void removeFromCart(Product product) {
        if(cart != null && !cart.isEmpty()){
            cart.remove(product);

            log.info("Removed " + product.getName() + " from cart.");
        }
    }

    public boolean containsProduct(Product product){
        if(cart != null && !cart.isEmpty()){
            return cart.contains(product);
        }

        return false;
    }

    private void calculateSum(){
        if(cart == null || cart.isEmpty()){
            sum = 0;
        } else {
            sum = cart.stream().mapToDouble(Product::getPrice).sum();
        }
    }

}
