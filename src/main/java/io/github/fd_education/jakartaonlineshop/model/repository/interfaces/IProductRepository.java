package io.github.fd_education.jakartaonlineshop.model.repository.interfaces;

import io.github.fd_education.jakartaonlineshop.model.entities.Product;

import java.util.Collection;
import java.util.List;

public interface IProductRepository {
    Product getById(Long id);

    List<Product> getAll();

    byte[] getImageOfProductById(Long id);

    void create(Product product);

    void createMany(Collection<Product> products);

    void update(Product product);

    void remove(Product product);
}
