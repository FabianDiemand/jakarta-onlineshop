package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity @Table(schema = "ONLINESHOP", name = "ORDER")
@Getter @Setter
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "is_payed")
    private boolean isPayed;

    @Column(name = "payed_at")
    private Date payedAt;

    @Column(name = "customer")
    private int customerForeignKey;

    @Column(name = "billing_address")
    private int billingAddressForeignKey;

    @Column(name = "shipping_address")
    private int shippingAddressForeignKey;

    @Column(name = "order_status")
    private int orderStatusForeignKey;

    @OneToOne
    @JoinColumn(name = "order_status", referencedColumnName = "status_id")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "billing_address", referencedColumnName = "address_id", nullable = false)
    private Address billingAddress;
    @ManyToOne
    @JoinColumn(name = "shipping_address", referencedColumnName = "address_id", nullable = false)
    private Address shippingAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        int id = orderId;
        final int prime = 31;
        return prime + ((Integer) id).hashCode();
    }
}
