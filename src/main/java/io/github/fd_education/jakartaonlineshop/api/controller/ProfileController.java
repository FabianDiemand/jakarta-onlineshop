package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Address;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Place;
import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import io.github.fd_education.jakartaonlineshop.model.repository.CustomerRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@Named
@SessionScoped
@Setter
@Getter
public class ProfileController implements Serializable {
    private final static Logger log = Logger.getLogger(ProfileController.class.toString());

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    @Setter(AccessLevel.NONE)
    private Customer customer;

    @Inject
    private Address address;

    @Inject
    private Place place;

    public void updateCustomer() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        try {
            address.setPlace(place);
            customer.setAddress(address);
            customer = customerRepository.update(customer);

            address = customer.getAddress();
            place = customer.getAddress().getPlace();

            FacesMessage m = new FacesMessage(bundle.getString("profile.update_success"));
            context.addMessage("profile_form", m);
        } catch (Exception exception) {
            FacesMessage fm = new FacesMessage(bundle.getString("profile.update_failure"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            context.addMessage("profile_form", fm);

            log.warning(exception.toString());
        }
    }

    public void initializeProfile(Long id) {
        customer = customerRepository.getById(id);

        address = customer.getAddress();
        place = address.getPlace();

        log.info("Profile initialized for customer " + customer.getFirstName() + " " + customer.getLastName());
    }

    public boolean isEmptyWishlist() {
        return customer.getWishlist().isEmpty();
    }

    public void addToWishlist(Product product) {
        log.info("Add product " + product.getName() + " to wishlist of " + customer.getFirstName() + " " + customer.getLastName());

        customer.addToWishlist(product);
        customer = customerRepository.update(customer);
    }

    public void removeFromWishlist(Product product) {
        log.info("Remove product " + product.getName() + " from wishlist of " + customer.getFirstName() + " " + customer.getLastName());

        customer.removeFromWishlist(product);
        customer = customerRepository.update(customer);
    }

    public void fetchCustomer() {
        customer = customerRepository.getById(customer.getId());
    }
}
