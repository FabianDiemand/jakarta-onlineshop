package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Address;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Place;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

@Named @RequestScoped
@Getter @Setter
public class RegisterController implements Serializable {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @Inject
    private Customer customer;

    @Inject
    private Address address;

    @Inject
    private Place place;

    public String persist() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(isExistingCustomer(customer.getEmail())){
            FacesMessage m = new FacesMessage(bundle.getString("customer_exists"));
            m.setSeverity(FacesMessage.SEVERITY_WARN);
            context.addMessage("registration-form", m);

            return "/register.jsf";
        }

        try {
            ut.begin();

            address.setPlace(place);
            customer.setAddress(address);
            em.persist(customer);

            ut.commit();

            FacesMessage m = new FacesMessage(bundle.getString("register_success"));
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage("registration-form", m);

        } catch(Exception e){
            FacesMessage fm = new FacesMessage(bundle.getString("register_failure"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage("registration-form", fm);
        }

        return "/register.jsf?faces-redirect=true";
    }

    private boolean isExistingCustomer(String email){
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByEmail", Customer.class);
        query.setParameter("email", email);

        return !query.getResultList().isEmpty();
    }
}
