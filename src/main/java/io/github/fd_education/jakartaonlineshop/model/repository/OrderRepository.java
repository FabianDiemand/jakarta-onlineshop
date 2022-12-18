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

/**
 * Order repository as an abstraction of data layer access.
 *
 * @author Fabian Diemand
 */
public class OrderRepository implements IOrderRepository, Serializable {
    private final static Logger log = Logger.getLogger(OrderRepository.class.toString());
    @Resource
    UserTransaction ut;

    @PersistenceContext
    EntityManager em;

    /**
     * Get all orders of a customer.
     *
     * @param customer the customer whose orders to fetch.
     * @return a list of orders
     */
    @Override
    public List<Order> getByCustomer(Customer customer) {
        TypedQuery<Order> query = em.createNamedQuery("Order.findByCustomer", Order.class);
        query.setParameter("customer", customer);
        return query.getResultList();
    }

    /**
     * Create an order in the database
     *
     * @param order the order to be persisted
     */
    @Override
    public void create(Order order) {
        try {
            ut.begin();
            em.persist(order);
            ut.commit();
        } catch (Exception ex) {
            log.severe(ex.toString());

            try{
                ut.rollback();
            } catch(Exception e){
                log.severe(e.toString());
            }
        } finally {
            em.close();
        }
    }

    /**
     * Update an order in the database.
     *
     * @param order the order to update
     */
    @Override
    public void update(Order order) {
        try {
            ut.begin();
            em.merge(order);
            ut.commit();
        } catch (Exception ex) {
            log.severe(ex.toString());

            try{
                ut.rollback();
            } catch(Exception e){
                log.severe(e.toString());
            }
        } finally {
            em.close();
        }
    }

    /**
     * Remove an order from the database.
     *
     * @param order the order to remove
     */
    @Override
    public void remove(Order order) {
        try {
            ut.begin();
            em.remove(order);
            ut.commit();
        } catch (Exception ex) {
            log.severe(ex.toString());

            try{
                ut.rollback();
            } catch(Exception e){
                log.severe(e.toString());
            }
        } finally {
            em.close();
        }
    }
}
