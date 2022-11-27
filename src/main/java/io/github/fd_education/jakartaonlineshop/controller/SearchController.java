package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class SearchController implements Serializable {
    private static final long serialVersionUID = 1L;

    private final static Logger log = Logger
            .getLogger(SearchController.class.toString());

    @PersistenceContext
    private EntityManager em;

    private List<Product> products;

    public List<Product> getProducts() {
        products = findAll();
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> findAll() {
        try {
            TypedQuery<Product> query = em.createNamedQuery(
                            "Product.findAll",
                    Product.class);
            return query.getResultList();
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        return new ArrayList<>();
    }
}
