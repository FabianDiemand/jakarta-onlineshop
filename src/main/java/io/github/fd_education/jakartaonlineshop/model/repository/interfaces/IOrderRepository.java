package io.github.fd_education.jakartaonlineshop.model.repository.interfaces;

import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Order;

import java.util.List;

/**
 * The interface to be used for the order repository.
 *
 * @author Fabian Diemand
 */
public interface IOrderRepository {
    List<Order> getByCustomer(Customer customer);

    void create(Order order);

    void update(Order order);

    void remove(Order order);
}
