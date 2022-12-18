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
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Customer repository as an abstraction of data layer access.
 *
 * @author Fabian Diemand
 */
@NoArgsConstructor
public class CustomerRepository implements ICustomerRepository, Serializable {
    private final static Logger log = Logger.getLogger(CustomerRepository.class.toString());
    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    /**
     * Return a customer identified by its ID.
     *
     * @param id the id of the customer to look up
     * @return a customer object or null if the ID cannot be resolved
     */
    @Override
    public Customer getById(Long id) {
        Customer c = em.find(Customer.class, id);
        log.info("Found customer for id " + id + "\n" + c);
        return c;
    }

    /**
     * Return a customer identified by its email.
     *
     * @param email the email of the customer to look up
     * @return a customer object or null if the email cannot be resolved
     */
    @Override
    public Customer getByEmail(String email) {
        try {
            TypedQuery<Customer> query = em.createNamedQuery("Customer.findByEmail", Customer.class);
            query.setParameter("email", email);
            Customer c = query.getSingleResult();

            log.info("Found customer for email " + email + "\n" + c);
            return c;
        } catch (Exception ex) {
            log.severe("Exception when trying to fetch customer by email: " + ex + "\n" + Arrays.toString(ex.getStackTrace()));
        }

        log.info("Found no customer for email " + email);
        return null;
    }

    /**
     * Create a customer in the database.
     *
     * @param customer the customer to be persisted
     */
    @Override
    public void create(Customer customer) {
        try {
            ut.begin();
            em.persist(customer);
            ut.commit();
            log.info("Successfully persisted customer " + customer);
        } catch (Exception ex) {
            log.severe("Rollback: Exception " + ex + " when attempting to persist customer " + customer + "\n" + Arrays.toString(ex.getStackTrace()));

            try{
                ut.rollback();

            } catch(Exception e){
                log.severe(e.toString());
            }
        }
    }

    /**
     * Create many customers in the database.
     *
     * @param customers a collection of customers to be persisted
     */
    @Override
    public void createMany(Collection<Customer> customers) {
        try {
            ut.begin();

            for(Customer customer: customers){
                em.persist(customer);
            }

            ut.commit();
            log.info("Successfully persisted " + customers.size() + " customers.");
        } catch (Exception ex) {
            log.severe("Rollback: Exception " + ex + " when attempting to persist " + customers.size() + " customers.\n" + Arrays.toString(ex.getStackTrace()));

            try{
                ut.rollback();
            } catch(Exception e){
                log.severe(e.toString());
            }
        }
    }

    /**
     * Update a customer in the database
     *
     * @param customer the customer to merge in the database
     * @return the managed customer object
     */
    @Override
    public Customer update(Customer customer) {
        Customer c = null;

        try {
            ut.begin();
            c = em.merge(customer);
            ut.commit();
            log.info("Successfully updated customer " + customer);
        } catch (Exception ex) {
            log.severe("Rollback: Exception " + ex + " when attempting to update customer " + customer + "\n" + Arrays.toString(ex.getStackTrace()));

            try{
                ut.rollback();
            } catch(Exception e){
                log.severe(e.toString());
            }
        }

        return c;
    }
}
