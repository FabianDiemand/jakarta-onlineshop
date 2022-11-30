package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.ejb.register.RegisterBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

@Named @RequestScoped
@Getter @Setter
public class RegisterController implements Serializable {
    private String firstname;
    private String lastname;
    private String phone;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String placeName;
    private String email;
    private String password;

    @EJB
    RegisterBeanLocal registerBeanLocal;

    public String persist() {
        try {
            registerBeanLocal.persist(firstname, lastname, phone, street, houseNumber, postalCode, placeName, email, password);

            FacesMessage m = new FacesMessage("Successfully registered.");
            FacesContext.getCurrentInstance().addMessage("registerForm", m);
        } catch(Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), e.getCause().getMessage());
            FacesContext.getCurrentInstance().addMessage("registerForm", fm);
        }

        return "/register.jsf";
    }

    public void isEmail(FacesContext fc, UIComponent uic, Object obj) throws ValidatorException {
        String value = (String) obj;

        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(value.isEmpty()){
            FacesMessage fm = new FacesMessage(bundle.getString("email_empty"));
            throw new ValidatorException(fm);
        }

        if(!Pattern.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$", value)){
            FacesMessage fm = new FacesMessage(bundle.getString("email_invalid"));
            throw new ValidatorException(fm);
        }
    }
}
