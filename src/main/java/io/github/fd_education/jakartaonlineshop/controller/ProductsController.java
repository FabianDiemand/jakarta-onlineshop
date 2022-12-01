package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.listeners.ProductListener;
import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.model.ArrayDataModel;
import jakarta.faces.model.DataModel;
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
public class ProductsController implements Serializable {
    private static final long serialVersionUID = 1L;

    private final static Logger log = Logger.getLogger(ProductsController.class.toString());

    @PersistenceContext
    private EntityManager em;

    public DataModel<Product> getProducts(){
        List<Product> list = findAll();
        Product[] products = list.toArray(new Product[list.size()]);

        DataModel<Product> dataModel = new ArrayDataModel<>(products);
        dataModel.addDataModelListener(new ProductListener());

        return dataModel;
    }

    public List<Product> findAll() {
        try {
            TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
            return query.getResultList();
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        return new ArrayList<>();
    }
}
