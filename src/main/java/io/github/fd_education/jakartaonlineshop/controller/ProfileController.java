package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Address;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Place;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@Named @SessionScoped
@Setter @Getter
public class ProfileController implements Serializable {
    private final static Logger log = Logger
            .getLogger(ProfileController.class.toString());

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @Inject
    @Setter(AccessLevel.NONE)
    private Customer customer;

    @Inject
    private Address address;

    @Inject
    private Place place;

    @Transactional
    public void updateCustomer(){
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        try{
            address.setPlace(place);
            customer.setAddress(address);

            customer = em.merge(customer);
            address = customer.getAddress();
            place = customer.getAddress().getPlace();

            FacesMessage m = new FacesMessage(bundle.getString("profile.update_success"));
            context.addMessage("profile_form", m);
        } catch(Exception exception){
            FacesMessage fm = new FacesMessage(bundle.getString("profile.update_failure"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            context.addMessage("profile_form", fm);

            log.warning(exception.toString());
        }
    }

    public void initializeProfile(Long id){
        customer = em.find(Customer.class, id);

        address = customer.getAddress();
        place = customer.getAddress().getPlace();
    }
}
