package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.domain.utils.HashingUtil;
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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@Named @SessionScoped
@Setter @Getter
public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;

    private final static Logger log = Logger
            .getLogger(LoginController.class.toString());

    @PersistenceContext
    private EntityManager em;

    private String email;

    private String password;

    @Inject
    private Customer customer;

    @Inject
    private ProfileController profileController;

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        try {
            TypedQuery<Customer> query = em.createNamedQuery("Customer.findByEmail", Customer.class);
            query.setParameter("email", email);
            customer = query.getSingleResult();

            if(HashingUtil.isMatchingHash(password, customer.getPassword())){
                profileController.initializeProfile(customer.getId());
                customer.setLoggedIn(true);
                return "/index.jsf";
            } else {
                FacesMessage m = new FacesMessage(bundle.getString("signin_failure"));
                m.setSeverity(FacesMessage.SEVERITY_WARN);
                context.addMessage("signin_form", m);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), e.getCause().getMessage());
            context.addMessage("signin_form", fm);
        }

        return "/signin.jsf";
    }

    public String logout(){
        customer.setLoggedIn(false);

        return "/index.jsf";
    }
}