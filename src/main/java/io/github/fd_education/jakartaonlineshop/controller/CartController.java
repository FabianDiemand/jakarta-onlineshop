package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named @SessionScoped
public class CartController implements Serializable {

    @Getter
    private List<Product> cart;

    private double sum;

    public void addToCart(Product product){
        if(cart == null){
            cart = new ArrayList<>();
        }

        cart.add(product);
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
}
