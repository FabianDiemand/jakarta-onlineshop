package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.api.listeners.ProductListener;
import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import io.github.fd_education.jakartaonlineshop.model.repository.ProductRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.model.ArrayDataModel;
import jakarta.faces.model.DataModel;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

/**
 * Controller Bean to manage products.
 *
 * @author Fabian Diemand
 */
@Named @RequestScoped
public class ProductsController implements Serializable {
    // Abstraction of the data layer with repository
    @Inject
    ProductRepository productRepository;

    /**
     * Get an observable list of all available products.
     *
     * @return observable list of products
     */
    public DataModel<Product> getAllProducts(){
        List<Product> list = productRepository.getAll();
        Product[] products = list.toArray(new Product[0]);

        DataModel<Product> dataModel = new ArrayDataModel<>(products);
        dataModel.addDataModelListener(new ProductListener());

        return dataModel;
    }
}
