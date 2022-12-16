package io.github.fd_education.jakartaonlineshop.domain.pojo;

import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
public class Cart implements Serializable {
    @Getter
    private Set<Product> products;

    private double sum;

    public void addProduct(Product product){
        if(products == null){
            products = new HashSet<>();
        }

        products.add(product);
    }

    public void removeProduct(Product product){
        if(products != null && !products.isEmpty()){
            products.remove(product);
        }
    }

    public boolean containsProduct(Product product){
        if(products != null && !products.isEmpty()){
            return products.contains(product);
        }

        return false;
    }

    public boolean isEmpty(){
        return products == null || products.isEmpty();
    }

    public void clear(){
        products.clear();
    }

    public double getSum(){
        calculateSum();

        return sum;
    }

    private void calculateSum(){
        if(products == null || products.isEmpty()){
            sum = 0;
        } else {
            sum = products.stream().mapToDouble(Product::getPrice).sum();
        }
    }
}
