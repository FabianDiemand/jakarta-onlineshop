package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "is_payed")
    private boolean isPayed;
    @Basic
    @Column(name = "payed_at")
    private Date payedAt;
    @Basic
    @Column(name = "customer")
    private int customer;
    @Basic
    @Column(name = "billing_address")
    private int billingAddress;
    @Basic
    @Column(name = "shipping_address")
    private int shippingAddress;
    @Basic
    @Column(name = "order_status")
    private int orderStatus;
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "customer_id", nullable = false)
    private Customer customerByCustomer;
    @ManyToOne
    @JoinColumn(name = "billing_address", referencedColumnName = "address_id", nullable = false)
    private Address addressByBillingAddress;
    @ManyToOne
    @JoinColumn(name = "shipping_address", referencedColumnName = "address_id", nullable = false)
    private Address addressByShippingAddress;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    public Date getPayedAt() {
        return payedAt;
    }

    public void setPayedAt(Date payedAt) {
        this.payedAt = payedAt;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(int billingAddress) {
        this.billingAddress = billingAddress;
    }

    public int getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(int shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (isPayed != order.isPayed) return false;
        if (customer != order.customer) return false;
        if (billingAddress != order.billingAddress) return false;
        if (shippingAddress != order.shippingAddress) return false;
        if (orderStatus != order.orderStatus) return false;
        if (payedAt != null ? !payedAt.equals(order.payedAt) : order.payedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (isPayed ? 1 : 0);
        result = 31 * result + (payedAt != null ? payedAt.hashCode() : 0);
        result = 31 * result + customer;
        result = 31 * result + billingAddress;
        result = 31 * result + shippingAddress;
        result = 31 * result + orderStatus;
        return result;
    }

    public Customer getCustomerByCustomer() {
        return customerByCustomer;
    }

    public void setCustomerByCustomer(Customer customerByCustomer) {
        this.customerByCustomer = customerByCustomer;
    }

    public Address getAddressByBillingAddress() {
        return addressByBillingAddress;
    }

    public void setAddressByBillingAddress(Address addressByBillingAddress) {
        this.addressByBillingAddress = addressByBillingAddress;
    }

    public Address getAddressByShippingAddress() {
        return addressByShippingAddress;
    }

    public void setAddressByShippingAddress(Address addressByShippingAddress) {
        this.addressByShippingAddress = addressByShippingAddress;
    }
}
