package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Cart_Product", schema = "onlineshop", catalog = "jak_onlineshop")
public class CartProduct {
    @Basic
    @Column(name = "quantity")
    private int quantity;
    @Basic
    @Column(name = "cart")
    private int cart;
    @Basic
    @Column(name = "product")
    private int product;
    @ManyToOne
    @JoinColumn(name = "cart", referencedColumnName = "cart_id", nullable = false)
    private Cart cartByCart;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCart() {
        return cart;
    }

    public void setCart(int cart) {
        this.cart = cart;
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

        CartProduct that = (CartProduct) o;

        if (quantity != that.quantity) return false;
        if (cart != that.cart) return false;
        if (product != that.product) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = quantity;
        result = 31 * result + cart;
        result = 31 * result + product;
        return result;
    }

    public Cart getCartByCart() {
        return cartByCart;
    }

    public void setCartByCart(Cart cartByCart) {
        this.cartByCart = cartByCart;
    }
}
