package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Named @SessionScoped
@Setter @Getter
public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    private String email;

    private String password;

    @Inject
    private Customer customer;

    public String login() {
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.email= :email AND c.password= :password", Customer.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            List<Customer> customers = query.getResultList();
            if(customers.isEmpty()) {
                FacesMessage m = new FacesMessage("Signin in was not successful! Sorry, try again!");
                m.setSeverity(FacesMessage.SEVERITY_WARN);
                FacesContext.getCurrentInstance().addMessage("signin_form", m);
            } else {
                customer = customers.get(0);
                FacesMessage m = new FacesMessage("Succesfully signed in! You signed in under id " + customer.getId());

                customer.setLoggedIn(true);

                FacesContext.getCurrentInstance().addMessage("signin_form", m);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), e.getCause().getMessage());
            FacesContext.getCurrentInstance().addMessage("signin_form", fm);
        }

        return "/signin.jsf";
    }

    public String logout(){
        customer.setLoggedIn(false);

        return "/index.jsf";
    }
}