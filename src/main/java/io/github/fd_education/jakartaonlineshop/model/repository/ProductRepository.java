package io.github.fd_education.jakartaonlineshop.model.repository;

import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import io.github.fd_education.jakartaonlineshop.model.repository.interfaces.IProductRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

public class ProductRepository implements IProductRepository, Serializable {
    private final static Logger log = Logger
            .getLogger(ProductRepository.class.toString());
    @Resource
    UserTransaction ut;

    @PersistenceContext
    EntityManager em;

    @Override
    public Product getById(Long id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> getAll() {
        TypedQuery<Product> query = em.createNamedQuery("Product.findAllAvailable", Product.class);
        return query.getResultList();
    }

    @Override
    public void create(Product product) {
        try {
            ut.begin();
            em.persist(product);
            ut.commit();
        } catch (Exception ex) {
            log.severe(ex.toString());
        }
    }

    @Override
    public void update(Product product) {
        try {
            ut.begin();
            em.merge(product);
            ut.commit();
        } catch (Exception ex) {
            log.severe(ex.toString());
        }
    }

    @Override
    public void remove(Product product) {
        try {
            ut.begin();
            em.remove(product);
            ut.commit();
        } catch (Exception ex) {
            log.severe(ex.toString());
        }
    }
}
