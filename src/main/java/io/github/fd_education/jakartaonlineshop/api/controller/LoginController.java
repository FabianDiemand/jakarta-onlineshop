package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.domain.utils.HashingUtil;
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
import java.util.ResourceBundle;
import java.util.logging.Logger;

@Named @SessionScoped
@Setter @Getter
public class LoginController implements Serializable {
    private final static Logger log = Logger.getLogger(LoginController.class.toString());

    private String email, password;

    @Inject
    private Customer customer;

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private ProfileController profileController;

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        customer = customerRepository.getByEmail(email);

        if(customer == null || !HashingUtil.isMatchingHash(password, customer.getPassword())){
            log.warning("Login failed for email" + email);
            FacesMessage m = new FacesMessage(bundle.getString("signin_failure"));
            m.setSeverity(FacesMessage.SEVERITY_WARN);
            context.addMessage("signin_form", m);
            return "/signin.jsf";
        } else {
            log.info("Login successfull for customer " + customer.getFirstName() + " " + customer.getLastName());
            profileController.initializeProfile(customer.getId());
            customer.setLoggedIn(true);
            return "/index.jsf";
        }
    }

    @SuppressWarnings("SameReturnValue")
    public String logout(){
        customer.setLoggedIn(false);
        return "/index.jsf";
    }
}
