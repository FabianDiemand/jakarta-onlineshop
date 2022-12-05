package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named @SessionScoped
public class CartController implements Serializable {

    private final static Logger log = Logger
            .getLogger(CartController.class.toString());

    @Getter
    private List<Product> cart;

    private double sum;

    public void addToCart(Product product){

        log.info("CALLED: Add " + product + " to cart.");

        if(cart == null){
            cart = new ArrayList<>();
        }

        if(!cart.contains(product)){
            cart.add(product);
        }

        log.info("COMPLETED: Added " + product + " to cart.");
    }

    public double getSum(){
        calculateSum();

        return sum;
    }

    public boolean hasProducts(){
        return cart != null && !cart.isEmpty();
    }

    private void calculateSum(){
        if(cart == null || cart.isEmpty()){
            sum = 0;
        } else {
            sum = cart.stream().mapToDouble(Product::getPrice).sum();
        }
    }

    public void removeFromCart(Product product) {
        log.info("CALLED: Remove " + product + " from cart.");

        if(cart != null && !cart.isEmpty()){
            cart.remove(product);

            log.info("COMPLETED: Removed " + product + " from cart.");
        }
    }
}
