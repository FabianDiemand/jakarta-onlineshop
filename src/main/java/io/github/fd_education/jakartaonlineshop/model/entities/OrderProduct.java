package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(schema = "ONLINESHOP", name = "ORDERPRODUCT")
@Getter @Setter
public class OrderProduct {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private int orderProductId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product")
    private int productForeignKey;

    @Column(name = "order")
    private int orderForeignKey;

    @ManyToOne
    @JoinColumn(name = "order", referencedColumnName = "order_id", nullable = false)
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProduct that = (OrderProduct) o;

        if (orderProductId != that.orderProductId) return false;
        if (quantity != that.quantity) return false;
        if (productForeignKey != that.productForeignKey) return false;
        if (orderForeignKey != that.orderForeignKey) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderProductId;
        result = 31 * result + quantity;
        result = 31 * result + productForeignKey;
        result = 31 * result + orderForeignKey;
        return result;
    }
}
