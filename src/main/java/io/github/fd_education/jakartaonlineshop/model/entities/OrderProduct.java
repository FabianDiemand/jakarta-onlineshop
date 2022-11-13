package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;

@Entity
public class OrderProduct {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_product_id")
    private int orderProductId;
    @Basic
    @Column(name = "quantity")
    private int quantity;
    @Basic
    @Column(name = "product")
    private int product;
    @Basic
    @Column(name = "order")
    private int order;
    @ManyToOne
    @JoinColumn(name = "order", referencedColumnName = "order_id", nullable = false)
    private Order orderByOrder;

    public int getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(int orderProductId) {
        this.orderProductId = orderProductId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProduct that = (OrderProduct) o;

        if (orderProductId != that.orderProductId) return false;
        if (quantity != that.quantity) return false;
        if (product != that.product) return false;
        if (order != that.order) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderProductId;
        result = 31 * result + quantity;
        result = 31 * result + product;
        result = 31 * result + order;
        return result;
    }

    public Order getOrderByOrder() {
        return orderByOrder;
    }

    public void setOrderByOrder(Order orderByOrder) {
        this.orderByOrder = orderByOrder;
    }
}
