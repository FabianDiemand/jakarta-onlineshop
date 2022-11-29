package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.ejb.register.RegisterBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
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

    public String persist() {
        try {
            registerBeanLocal.persist(firstname, lastname, phone, street, houseNumber, postalCode, placeName, email, password);

            FacesMessage m = new FacesMessage("Successfully registered.");
            FacesContext.getCurrentInstance().addMessage("registerForm", m);
        } catch(Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), e.getCause().getMessage());
            FacesContext.getCurrentInstance().addMessage("registerForm", fm);
        }

        return "/register.jsf";
    }
}
