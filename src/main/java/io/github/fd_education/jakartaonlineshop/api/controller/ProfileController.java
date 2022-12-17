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

/**
 * Controller Bean to manage the customer profile.
 *
 * @author Fabian Diemand
 */
@Named @SessionScoped
@Setter @Getter
public class ProfileController implements Serializable {
    private final static Logger log = Logger.getLogger(ProfileController.class.toString());

    // Customer object to hold all customer data
    @Inject
    @Setter(AccessLevel.NONE)
    private Customer customer;

    // Address object for the customer address
    @Inject
    private Address address;

    // Place object for the address place
    @Inject
    private Place place;

    // Abstraction of the data layer with a repository
    @Inject
    private CustomerRepository customerRepository;

    /**
     * Update the customer according to the data entered in the profile form.
     */
    public void updateCustomer() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        try {
            // Set place and address and update customer
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

    /**
     * Initialize the profile with a customer identified by their id
     *
     * @param id the id of the customer to provide the profile for
     */
    public void initializeProfile(Long id) {
        customer = customerRepository.getById(id);

        address = customer.getAddress();
        place = address.getPlace();

        log.info("Profile initialized for customer " + customer.getFirstName() + " " + customer.getLastName());
    }

    /**
     * Check if the wishlist is empty.
     *
     * @return true if the wishlist is empty, false otherwise
     */
    public boolean isEmptyWishlist() {
        return customer.getWishlist().isEmpty();
    }

    /**
     * Add a product to the wishlist.
     *
     * @param product the product to be added to the wishlist
     */
    public void addToWishlist(Product product) {
        log.info("Add product " + product.getName() + " to wishlist of " + customer.getFirstName() + " " + customer.getLastName());

        customer.addToWishlist(product);
        customer = customerRepository.update(customer);
    }

    /**
     * Remove a product from the wishlist
     *
     * @param product the product to remove from the wishlist
     */
    public void removeFromWishlist(Product product) {
        log.info("Remove product " + product.getName() + " from wishlist of " + customer.getFirstName() + " " + customer.getLastName());

        customer.removeFromWishlist(product);
        customer = customerRepository.update(customer);
    }

    /**
     * Fetch the customer from the database to have current data.
     */
    public void fetchCustomer() {
        customer = customerRepository.getById(customer.getId());
    }
}
