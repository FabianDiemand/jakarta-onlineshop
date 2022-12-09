package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Order;
import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Set;

@Named @RequestScoped
public class OrderController {

    @Inject
    Order order;

    public void createOrderFromCart(Customer customer, Set<Product> cart){

    }
}
