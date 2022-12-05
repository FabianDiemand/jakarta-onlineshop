package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Address;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Place;
import io.github.fd_education.jakartaonlineshop.model.entities.Product;
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

    public void updateCustomer(){
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        try{
            ut.begin();

            address.setPlace(place);
            customer.setAddress(address);

            customer = em.merge(customer);
            address = customer.getAddress();
            place = customer.getAddress().getPlace();

            ut.commit();

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

        log.info("Profile initialized for customer " + customer.getFirstName() + " " + customer.getLastName());
    }

    public void addToWishlist(Product product){
        log.info("Add product " + product.getName() + " to wishlist of " + customer.getFirstName() + " " + customer.getLastName());

        try{
            ut.begin();
            customer.addToWishlist(product);
            customer = em.merge(customer);
            ut.commit();
        } catch(Exception exception){
            log.warning(exception.getMessage());
        }

    }

    public void removeFromWishlist(Product product){
        log.info("Remove product " + product.getName() + " from wishlist of " + customer.getFirstName() + " " + customer.getLastName());

        try{
            ut.begin();
            customer.removeFromWishlist(product);
            customer = em.merge(customer);
            ut.commit();
        } catch(Exception exception){
            log.warning(exception.getMessage());
        }
    }
}
