package io.github.fd_education.jakartaonlineshop.model.repository;

import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import io.github.fd_education.jakartaonlineshop.model.repository.interfaces.IProductRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Product repository as an abstraction of data layer access.
 *
 * @author Fabian Diemand
 */
public class ProductRepository implements IProductRepository, Serializable {
    private final static Logger log = Logger.getLogger(ProductRepository.class.toString());
    @Resource
    UserTransaction ut;

    @PersistenceContext
    EntityManager em;

    /**
     * Get a product by its ID.
     *
     * @param id the id of the product to look up
     * @return a product object
     */
    @Override
    public Product getById(Long id) {
        return em.find(Product.class, id);
    }

    /**
     * Get all available products in the database
     *
     * @return all products that are not sold
     */
    @Override
    public List<Product> getAll() {
        TypedQuery<Product> query = em.createNamedQuery("Product.findAllAvailable", Product.class);
        return query.getResultList();
    }

    /**
     * Get the product image of a certain product identified by its ID.
     *
     * @param id the id of the product whose image should be fetched
     * @return a byte array with the image
     */
    @Override
    public byte[] getImageOfProductById(Long id){
        Query query = em.createQuery("SELECT p.image FROM Product  p WHERE p.id = :id");
        query.setParameter("id", id);

        return (byte[]) query.getSingleResult();
    }

    /**
     * Create a product in the database.
     *
     * @param product the product to be persisted
     */
    @Override
    public void create(Product product) {
        try {
            ut.begin();
            em.persist(product);
            ut.commit();
        } catch (Exception ex) {
            log.severe(ex.toString());

            try{
                ut.rollback();
            } catch(Exception e){
                log.severe(e.toString());
            }
        }
    }

    /**
     * Create multiple products in the database
     *
     * @param products a collection of products to be persisted
     */
    @Override
    public void createMany(Collection<Product> products) {
        try {
            ut.begin();

            for(Product product: products){
                em.persist(product);
            }

            ut.commit();
        } catch (Exception ex) {
            log.severe(ex.toString());

            try{
                ut.rollback();
            } catch(Exception e){
                log.severe(e.toString());
            }
        }
    }

    /**
     * Update a product in the database.
     *
     * @param product the updated product
     */
    @Override
    public void update(Product product) {
        try {
            ut.begin();
            em.merge(product);
            ut.commit();
        } catch (Exception ex) {
            log.severe(ex.toString());

            try{
                ut.rollback();
            } catch(Exception e){
                log.severe(e.toString());
            }
        }
    }
}
