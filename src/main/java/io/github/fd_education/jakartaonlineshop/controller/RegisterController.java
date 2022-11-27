package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.controller.ejb.register.RegisterBeanLocal;
import io.github.fd_education.jakartaonlineshop.model.entities.Address;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Place;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Named
@RequestScoped
@Getter @Setter
public class RegisterController implements Serializable {

    private String firstname;
    private String lastname;
    private String phone;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String placeName;
    private String email;
    private String password;

    @EJB
    RegisterBeanLocal registerBeanLocal;

    public String persist(){
        registerBeanLocal.persist(firstname, lastname, phone, street, houseNumber, postalCode, placeName, email, password);

        return "/register.jsf";
    }
}
