package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Address;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Place;
import io.github.fd_education.jakartaonlineshop.model.repository.CustomerRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controller Bean for the registration process.
 *
 * @author Fabian Diemand
 */
@Named @RequestScoped
@Getter @Setter
public class RegisterController implements Serializable {
    // Customer object to hold all customer data
    @Inject
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
     * Create the new customer according to the form in the profile.
     *
     * @return outcome of register, either with a reset form or with the data still inside
     */
    public String persist() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        // Check if the email address is already blocked
        if(customerRepository.getByEmail(customer.getEmail()) != null){
            FacesMessage m = new FacesMessage(bundle.getString("customer_exists"));
            m.setSeverity(FacesMessage.SEVERITY_WARN);
            context.addMessage("registration-form", m);

            return "/register.jsf";
        }

        // Set the address and place for the customer and persist
        try {
            address.setPlace(place);
            customer.setAddress(address);
            customerRepository.create(customer);

            FacesMessage m = new FacesMessage(bundle.getString("register_success"));
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage("registration-form", m);

        } catch(Exception e){
            FacesMessage fm = new FacesMessage(bundle.getString("register_failure"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage("registration-form", fm);
        }

        // Clear the form after successfully persisting the customer
        return "/register.jsf?faces-redirect=true";
    }
}
