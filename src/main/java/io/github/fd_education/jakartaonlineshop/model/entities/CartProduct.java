package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(schema = "ONLINESHOP", name = "CARTPRODUCT")
@Getter @Setter
public class CartProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_product_id")
    private int cartProductId;

    private int quantity;

    private int cart;

    private int product;
    @ManyToOne
    @JoinColumn(name = "cart", referencedColumnName = "cart_id", nullable = false)
    private Cart cartByCart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartProduct cartProduct = (CartProduct) o;
        return cartProductId == cartProduct.cartProductId;
    }

    @Override
    public int hashCode() {
        int id = cartProductId;
        final int prime = 31;
        return prime + ((Integer) id).hashCode();
    }
    public Cart getCartByCart() {
        return cartByCart;
    }

    public void setCartByCart(Cart cartByCart) {
        this.cartByCart = cartByCart;
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "cartProductId=" + cartProductId +
                ", quantity=" + quantity +
                ", cart=" + cart +
                ", product=" + product +
                ", cartByCart=" + cartByCart +
                '}';
    }
}
