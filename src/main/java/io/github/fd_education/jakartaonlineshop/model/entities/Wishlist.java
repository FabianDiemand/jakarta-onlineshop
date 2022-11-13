package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;

@Entity
public class Wishlist {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "wishlist_id")
    private int wishlistId;
    @Basic
    @Column(name = "customer")
    private int customer;
    @Basic
    @Column(name = "product")
    private int product;
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "customer_id", nullable = false)
    private Customer customerByCustomer;
    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "product_id", nullable = false)
    private Product productByProduct;

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wishlist wishlist = (Wishlist) o;

        if (wishlistId != wishlist.wishlistId) return false;
        if (customer != wishlist.customer) return false;
        if (product != wishlist.product) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wishlistId;
        result = 31 * result + customer;
        result = 31 * result + product;
        return result;
    }

    public Customer getCustomerByCustomer() {
        return customerByCustomer;
    }

    public void setCustomerByCustomer(Customer customerByCustomer) {
        this.customerByCustomer = customerByCustomer;
    }

    public Product getProductByProduct() {
        return productByProduct;
    }

    public void setProductByProduct(Product productByProduct) {
        this.productByProduct = productByProduct;
    }
}
