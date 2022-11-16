package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Collection;

@Entity @Table(schema = "ONLINESHOP", name = "CART")
@Getter @Setter
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_at")
    private Date createdAt;


    private int customer;

    @ManyToMany
    @JoinTable(
            name = "CartProduct",
            joinColumns =  @JoinColumn(name = "cart"),
            inverseJoinColumns = @JoinColumn(name = "product"))
    private Collection<Product> productsOfCart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;
        return cartId == cart.cartId;
    }

    @Override
    public int hashCode() {
        int id = cartId;
        final int prime = 31;
        return prime + ((Integer) id).hashCode();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", customer=" + customer +
                '}';
    }
}
