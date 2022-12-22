package io.github.fd_education.jakartaonlineshop.domain.pojo;

import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * POJO to implement shopping cart functionality.
 *
 * @author Fabian Diemand
 */
@NoArgsConstructor
public class Cart implements Serializable {
    @Getter
    private Set<Product> products;

    private double sum;

    /**
     * Add a product to the cart.
     *
     * @param product the product to add
     */
    public void addProduct(Product product){
        if(products == null){
            products = new HashSet<>();
        }

        products.add(product);
    }

    /**
     * Remove a product from the cart.
     *
     * @param product the product to remove
     */
    public void removeProduct(Product product){
        if(products != null && !products.isEmpty()){
            products.remove(product);
        }
    }

    /**
     * Check if the cart contains a certain product.
     *
     * @param product the product to look up
     * @return true if the product is in the cart, false otherwise
     */
    public boolean containsProduct(Product product){
        if(products != null && !products.isEmpty()){
            return products.contains(product);
        }

        return false;
    }

    /**
     * Check if the cart is empty.
     *
     * @return true if the cart is empty, false otherwise
     */
    public boolean isEmpty(){
        return products == null || products.isEmpty();
    }

    /**
     * Clear the cart.
     */
    public void clear(){
        products.clear();
    }

    /**
     * Get the total price of all products in the cart.
     *
     * @return the sum of all prices of products in the cart
     */
    @SuppressWarnings("unused")
    public double getSum(){
        calculateSum();

        return sum;
    }

    /**
     * Return total as String with two fraction digits.
     *
     * @return total with two fraction digits
     */
    @SuppressWarnings("unused")
    public String getSumString(){
        calculateSum();
        return String.format("%.2f", sum);
    }

    // Calculate the sum of all products in the cart
    private void calculateSum(){
        if(products == null || products.isEmpty()){
            sum = 0;
        } else {
            sum = products.stream().mapToDouble(Product::getPrice).sum();
        }
    }
}
