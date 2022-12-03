package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.ejb.register.RegisterBeanLocal;
import io.github.fd_education.jakartaonlineshop.utils.HashingUtil;
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
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

@Named @RequestScoped
@Getter @Setter
public class RegisterController implements Serializable {
    private String firstname;
    private String lastname;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String placeName;
    private String email;
    private String password;

    @EJB
    RegisterBeanLocal registerBeanLocal;

    public String persist() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        try {
            String passwordHash = HashingUtil.getHash(password);

            registerBeanLocal.persist(firstname, lastname, street, houseNumber, postalCode, placeName, email, passwordHash);

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

    public void isEmail(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) throws ValidatorException {
        String value = (String) obj;

        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(value.isEmpty()){
            FacesMessage fm = new FacesMessage(bundle.getString("email_empty"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }

        if(!Pattern.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", value)){
            FacesMessage fm = new FacesMessage(bundle.getString("email_invalid"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    public void isStrongPassword(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) throws ValidatorException {
        String value = (String) obj;

        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(value.isEmpty()){
            FacesMessage fm = new FacesMessage(bundle.getString("password_empty"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }

        //noinspection RegExpRedundantNestedCharacterClass
        if(!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", value)){
            FacesMessage fm = new FacesMessage(bundle.getString("password_weak"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    public void confirmPassword(FacesContext fc, UIComponent uic, Object obj) {
        String value = (String) obj;

        String otherPassword = (String) uic.getAttributes().get("password");

        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(!Objects.equals(value, otherPassword)){
            FacesMessage fm = new FacesMessage(bundle.getString("passwords_not_matching"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    public void isPlace(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) {
        String value = (String) obj;

        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(value.isEmpty()){
            FacesMessage fm = new FacesMessage(bundle.getString("place_empty"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    public void isPostalCode(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) {
        String value = (String) obj;

        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(value.isEmpty()){
            FacesMessage fm = new FacesMessage(bundle.getString("postal_code_empty"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    public void isHouseNumber(FacesContext fc, @SuppressWarnings("unused") UIComponent ignored_, Object obj) {
        String value = (String) obj;

        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(value.isEmpty()){
            FacesMessage fm = new FacesMessage(bundle.getString("house_number_empty"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    public void isStreet(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) {
        String value = (String) obj;

        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(value.isEmpty()){
            FacesMessage fm = new FacesMessage(bundle.getString("street_empty"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }


    public void isLastName(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) {
        String value = (String) obj;

        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(value.isEmpty()){
            FacesMessage fm = new FacesMessage(bundle.getString("lastname_empty"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }


    public void isFirstName(FacesContext fc, @SuppressWarnings("unused") UIComponent ignored_, Object obj) {
        String value = (String) obj;

        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(value.isEmpty()){
            FacesMessage fm = new FacesMessage(bundle.getString("firstname_empty"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }
}
