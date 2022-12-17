package io.github.fd_education.jakartaonlineshop.model.repository.interfaces;

import io.github.fd_education.jakartaonlineshop.model.entities.Customer;

import java.util.Collection;

public interface ICustomerRepository {
    Customer getById(Long id);

    Customer getByEmail(String email);

    void create(Customer customer);

    void createMany(Collection<Customer> customers);

    Customer update(Customer customer);

    Customer remove(Customer customer);
}
