package io.github.fd_education.jakartaonlineshop.model.repository.interfaces;

import io.github.fd_education.jakartaonlineshop.model.entities.Customer;

public interface ICustomerRepository {
    Customer getById(Long id);

    Customer getByEmail(String email);

    Customer create(Customer customer);

    Customer update(Customer customer);

    Customer remove(Customer customer);
}
