package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import jakarta.annotation.Resource;
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
public class RegisterController implements Serializable {
    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @Inject
    @Getter @Setter
    private Customer customer;

    public String persist(){
        try{
            ut.begin();
            em.persist(customer);
            ut.commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return "/register.jsf";
    }
}
