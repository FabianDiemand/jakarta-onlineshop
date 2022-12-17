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

@Named @RequestScoped
@Getter @Setter
public class RegisterController implements Serializable {
    @Inject
    private Customer customer;

    @Inject
    private Address address;

    @Inject
    private Place place;

    @Inject
    private CustomerRepository customerRepository;

    public String persist() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(customerRepository.getByEmail(customer.getEmail()) != null){
            FacesMessage m = new FacesMessage(bundle.getString("customer_exists"));
            m.setSeverity(FacesMessage.SEVERITY_WARN);
            context.addMessage("registration-form", m);

            return "/register.jsf";
        }

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

        return "/register.jsf?faces-redirect=true";
    }
}
