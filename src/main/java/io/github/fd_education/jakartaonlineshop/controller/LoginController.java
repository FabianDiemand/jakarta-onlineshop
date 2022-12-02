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
import java.util.Locale;
import java.util.ResourceBundle;

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
            TypedQuery<Customer> query = em.createQuery(
                    "SELECT c FROM Customer c WHERE c.email= :email AND c.password= :password", Customer.class);

            query.setParameter("email", email);
            query.setParameter("password", password);
            List<Customer> customers = query.getResultList();

            FacesContext context = FacesContext.getCurrentInstance();
            Locale locale = context.getViewRoot().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

            if(customers.isEmpty()) {
                FacesMessage m = new FacesMessage(bundle.getString("signin_failure"));
                m.setSeverity(FacesMessage.SEVERITY_WARN);
                FacesContext.getCurrentInstance().addMessage("signin_form", m);
            } else {
                customer = customers.get(0);
                customer.setLoggedIn(true);
                return "/index.jsf";
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
