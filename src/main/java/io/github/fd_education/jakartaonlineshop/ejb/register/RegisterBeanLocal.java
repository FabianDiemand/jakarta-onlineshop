package io.github.fd_education.jakartaonlineshop.ejb.register;

import jakarta.ejb.Local;

@Local
public interface RegisterBeanLocal {
    void persist(String firstname, String lastname, String phone, String street, String houseNumber, String postalCode, String placeName, String email, String password);
}
