package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(schema = "ONLINESHOP", name = "WISHLIST")
@Getter @Setter
public class Wishlist {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private int wishlistId;

    @Column(name = "customer")
    private int customerForeignKey;

    @Column(name = "product")
    private int productForeignKey;
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wishlist wishlist = (Wishlist) o;
        return wishlistId == wishlist.wishlistId;
    }

    @Override
    public int hashCode() {
        int id = wishlistId;
        final int prime = 31;
        return prime + ((Integer) id).hashCode();
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "wishlistId=" + wishlistId +
                ", customerForeignKey=" + customerForeignKey +
                ", productForeignKey=" + productForeignKey +
                ", customer=" + customer +
                ", product=" + product +
                '}';
    }
}
