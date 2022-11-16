package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Collection;

@Entity @Table(schema = "ONLINESHOP", name = "CUSTOMER")
@Getter @Setter
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private String password;

    private Date birthdate;

    @OneToMany(mappedBy = "customer")
    private Collection<Order> orders;

    @OneToOne(mappedBy = "customer")
    private Cart cart;

    @Column(name = "shipping_address")
    private int shippingAddressForeignKey;

    @Column(name = "billing_address")
    private int billingAddressForeignKey;

    @ManyToOne
    @JoinColumn(name = "shipping_address", referencedColumnName = "address_id")
    private Address shippingAddress;
    @ManyToOne
    @JoinColumn(name = "billing_address", referencedColumnName = "address_id")
    private Address billingAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;
        return customerId == customer.customerId;
    }

    @Override
    public int hashCode() {
        int id = customerId;
        final int prime = 31;
        return prime + ((Integer) id).hashCode();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", birthdate=" + birthdate +
                ", orders=" + orders +
                ", carts=" + cart +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                '}';
    }
}
