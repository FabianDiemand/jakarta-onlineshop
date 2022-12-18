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
import java.util.Arrays;
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
        List<Order> o = query.getResultList();
        log.info("Found " + o.size() + " orders for customer " + customer);

        return o;
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
            log.info("Successfully persisted order " + order);
        } catch (Exception ex) {
            log.severe("Rollback: Exception " + ex + " when attempting to persist order " + order + "\n" + Arrays.toString(ex.getStackTrace()));

            try{
                ut.rollback();
            } catch(Exception e){
                log.severe(e.toString());
            }
        }


    }

    /**
     * Update an order in the database.
     *
     * @param order the order to update
     */
    @Override
    public Order update(Order order) {
        Order o = null;

        try {
            ut.begin();
            o = em.merge(order);
            ut.commit();
            log.info("Successfully updated order " + order);
        } catch (Exception ex) {
            log.severe("Rollback: Exception " + ex + " when attempting to update order " + order + "\n" + Arrays.toString(ex.getStackTrace()));

            try{
                ut.rollback();
            } catch(Exception e){
                log.severe(e.toString());
            }
        }

        return o;
    }
}
