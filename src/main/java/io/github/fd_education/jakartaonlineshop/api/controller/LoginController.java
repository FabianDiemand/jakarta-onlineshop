package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.domain.utils.HashingUtil;
import io.github.fd_education.jakartaonlineshop.domain.utils.MessageUtil;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.repository.CustomerRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Controller Bean for the login process.
 *
 * @author Fabian Diemand
 */
@Named @SessionScoped
@Setter @Getter
public class LoginController implements Serializable {
    private final static Logger log = Logger.getLogger(LoginController.class.toString());

    // Login information the user has to pass to the application (via form at login.xhtml)
    private String email, password;

    // Customer object to hold all customer data and the login state
    @Inject
    private Customer customer;

    // Abstraction of the data layer with a repository
    @Inject
    private CustomerRepository customerRepository;

    // Inject profileController to set profile of the customer
    @Inject
    private ProfileController profileController;

    /**
     * Authenticate the user, using their email and password.
     *
     * @return outcome of index if login is successful, outcome of signin otherwise
     */
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();

        // Check if the customer exists and if the password hashes match
        customer = customerRepository.getByEmail(email);
        if(customer == null || !HashingUtil.isMatchingHash(password, customer.getPassword())){
            log.warning("Login failed for email" + email);
            FacesMessage fm = MessageUtil.getMessageWithSeverity(locale, "messages", "signin_failure", FacesMessage.SEVERITY_WARN);
            context.addMessage("signin_form", fm);
            return "/signin.jsf";
        } else {
            log.info("Login successfull for customer " + customer.getFirstName() + " " + customer.getLastName());
            profileController.initializeProfile(customer.getId());
            customer.setLoggedIn(true);
            return "/index.jsf";
        }
    }

    /**
     * Log out the customer.
     *
     * @return outcome of index
     */
    @SuppressWarnings("SameReturnValue")
    public String logout(){
        customer.setLoggedIn(false);
        return "/index.jsf";
    }
}
