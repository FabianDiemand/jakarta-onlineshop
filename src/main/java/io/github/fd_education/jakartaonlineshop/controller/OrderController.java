package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.Set;

@Named @RequestScoped
public class OrderController {

    public void createOrderFromCart(Set<Product> cart){

    }
}
