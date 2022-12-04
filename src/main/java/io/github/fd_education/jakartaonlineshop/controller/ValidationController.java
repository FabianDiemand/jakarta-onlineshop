package io.github.fd_education.jakartaonlineshop.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

@Named @RequestScoped
public class ValidationController implements Serializable {

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

    public void isEmail(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) throws ValidatorException {
        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        String value = (String) obj;

        if(value.isEmpty()){
            FacesMessage fm = new FacesMessage(bundle.getString("email_empty"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }

        if(isInvalidEmail(value)){
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
        if(isWeakPasswort(value)){
            FacesMessage fm = new FacesMessage(bundle.getString("password_weak"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    public void isMatchingPassword(FacesContext fc, UIComponent uic, Object obj) {
        String value = (String) obj;

        String otherPassword = (String) uic.getAttributes().get("password");

        Locale locale = fc.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(isNotMatchingPassword(value, otherPassword)){
            FacesMessage fm = new FacesMessage(bundle.getString("passwords_not_matching"));
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    private boolean isInvalidEmail(String value){
        String emailPattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        return !Pattern.matches(emailPattern, value);
    }

    @SuppressWarnings("RegExpRedundantNestedCharacterClass")
    private boolean isWeakPasswort(String value){
        String strongPasswordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$";
        return !Pattern.matches(strongPasswordPattern, value);
    }

    private boolean isNotMatchingPassword(String password, String value){
        return !Objects.equals(password, value);
    }
}
