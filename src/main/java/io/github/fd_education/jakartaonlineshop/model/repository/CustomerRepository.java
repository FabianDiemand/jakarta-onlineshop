package io.github.fd_education.jakartaonlineshop.model.repository;

import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.repository.interfaces.ICustomerRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.logging.Logger;

@NoArgsConstructor
public class CustomerRepository implements ICustomerRepository, Serializable {
    private final static Logger log = Logger
            .getLogger(CustomerRepository.class.toString());
    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @Override
    public Customer getById(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public Customer getByEmail(String email) {
        try {
            TypedQuery<Customer> query = em.createNamedQuery("Customer.findByEmail", Customer.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception ex) {
            log.severe(ex.toString());
        }

        return null;
    }

    @Override
    public Customer create(Customer customer) {
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        Customer c = null;

        try {
            ut.begin();
            c = em.merge(customer);
            ut.commit();
        } catch (Exception ex) {
            log.severe(ex.toString());
        }

        return c;
    }

    @Override
    public Customer remove(Customer customer) {
        return null;
    }
}
