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
import java.util.logging.Logger;

@Named
@RequestScoped
public class ProductsController implements Serializable {
    private static final long serialVersionUID = 1L;

    private final static Logger log = Logger.getLogger(ProductsController.class.toString());

    @Inject
    ProductRepository productRepository;

    public DataModel<Product> getAllProducts(){
        List<Product> list = productRepository.getAll();
        Product[] products = list.toArray(new Product[0]);

        DataModel<Product> dataModel = new ArrayDataModel<>(products);
        dataModel.addDataModelListener(new ProductListener());

        return dataModel;
    }
}
