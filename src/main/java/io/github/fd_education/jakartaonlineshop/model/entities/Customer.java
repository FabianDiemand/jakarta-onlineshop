package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id")
    private int customerId;
    @Basic
    @Column(name = "firstname")
    private String firstname;
    @Basic
    @Column(name = "lastname")
    private String lastname;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "birthdate")
    private Date birthdate;
    @Basic
    @Column(name = "shipping_address")
    private Integer shippingAddress;
    @Basic
    @Column(name = "billing_address")
    private Integer billingAddress;
    @OneToMany(mappedBy = "customerByCustomer")
    private Collection<Cart> cartsByCustomerId;
    @ManyToOne
    @JoinColumn(name = "shipping_address", referencedColumnName = "address_id")
    private Address addressByShippingAddress;
    @ManyToOne
    @JoinColumn(name = "billing_address", referencedColumnName = "address_id")
    private Address addressByBillingAddress;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Integer shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Integer getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Integer billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (customerId != customer.customerId) return false;
        if (firstname != null ? !firstname.equals(customer.firstname) : customer.firstname != null) return false;
        if (lastname != null ? !lastname.equals(customer.lastname) : customer.lastname != null) return false;
        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
        if (phone != null ? !phone.equals(customer.phone) : customer.phone != null) return false;
        if (password != null ? !password.equals(customer.password) : customer.password != null) return false;
        if (birthdate != null ? !birthdate.equals(customer.birthdate) : customer.birthdate != null) return false;
        if (shippingAddress != null ? !shippingAddress.equals(customer.shippingAddress) : customer.shippingAddress != null)
            return false;
        if (billingAddress != null ? !billingAddress.equals(customer.billingAddress) : customer.billingAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (shippingAddress != null ? shippingAddress.hashCode() : 0);
        result = 31 * result + (billingAddress != null ? billingAddress.hashCode() : 0);
        return result;
    }

    public Collection<Cart> getCartsByCustomerId() {
        return cartsByCustomerId;
    }

    public void setCartsByCustomerId(Collection<Cart> cartsByCustomerId) {
        this.cartsByCustomerId = cartsByCustomerId;
    }

    public Address getAddressByShippingAddress() {
        return addressByShippingAddress;
    }

    public void setAddressByShippingAddress(Address addressByShippingAddress) {
        this.addressByShippingAddress = addressByShippingAddress;
    }

    public Address getAddressByBillingAddress() {
        return addressByBillingAddress;
    }

    public void setAddressByBillingAddress(Address addressByBillingAddress) {
        this.addressByBillingAddress = addressByBillingAddress;
    }
}
