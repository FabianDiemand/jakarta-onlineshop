package io.github.fd_education.jakartaonlineshop.model.repository;

import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Order;
import io.github.fd_education.jakartaonlineshop.model.repository.interfaces.IOrderRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

public class OrderRepository implements IOrderRepository, Serializable {
    private final static Logger log = Logger.getLogger(OrderRepository.class.toString());
    @Resource
    UserTransaction ut;

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Order> getByCustomer(Customer customer) {
        TypedQuery<Order> query = em.createNamedQuery("Order.findByCustomer", Order.class);
        query.setParameter("customer", customer);
        return query.getResultList();
    }

    @Override
    public void create(Order order) {
        try {
            ut.begin();
            em.persist(order);
            ut.commit();
        } catch (Exception ex) {
            log.severe(ex.toString());
        }
    }

    @Override
    public void update(Order order) {
        try {
            ut.begin();
            em.merge(order);
            ut.commit();
        } catch (Exception ex) {
            log.severe(ex.toString());
        }
    }

    @Override
    public void remove(Order order) {
        try {
            ut.begin();
            em.remove(order);
            ut.commit();
        } catch (Exception ex) {
            log.severe(ex.toString());
        }
    }
}
