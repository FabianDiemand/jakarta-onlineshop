package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Order;
import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.logging.Logger;

@Named @RequestScoped
@Getter @Setter
public class OrderController {

    private final static Logger log = Logger
            .getLogger(OrderController.class.toString());
    @Inject
    Order order;

    @Resource
    UserTransaction ut;

    @PersistenceContext
    EntityManager em;

    public void createOrderFromCart(Customer customer, Set<Product> cart) {
        try {
            ut.begin();

            order.setOrderedAt(LocalDate.now());
            order.setCustomer(customer);
            order.setIsPaid(false);
            // TODO: Create ENUM for Order states
            order.setOrderStatus("ORDERED");
            order.setProducts(cart);

            em.persist(order);

            ut.commit();
        } catch (Exception exception) {
            log.info(exception.toString());
        }
    }
}
