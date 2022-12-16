package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.domain.pojo.Cart;
import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;
import java.util.logging.Logger;

@Named @SessionScoped
public class CartController implements Serializable {

    private final static Logger log = Logger
            .getLogger(CartController.class.toString());

    @Getter @Inject
    private Cart cart;

    public void addToCart(Product product){
        cart.addProduct(product);
        log.info("Add " + product.getName() + " to cart.");
    }

    public double getSum(){
        log.info("Sum of products in Cart is " + cart.getSum());
        return cart.getSum();
    }

    public boolean isEmpty(){
        return cart.isEmpty();
    }

    public void removeFromCart(Product product) {
        cart.removeProduct(product);
        log.info("Removed " + product.getName() + " from cart.");
    }

    public boolean containsProduct(Product product){
        return cart.containsProduct(product);
    }

    public void emptyCart(){
        cart.clear();
    }
}
