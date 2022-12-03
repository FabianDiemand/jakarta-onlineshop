package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Address;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Place;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;
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

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @Inject
    private Customer customer;

    @Inject
    private Address address;

    @Inject
    private Place place;

    public String persist() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        if(isExistingCustomer(customer.getEmail())){
            FacesMessage m = new FacesMessage(bundle.getString("customer_exists"));
            m.setSeverity(FacesMessage.SEVERITY_WARN);
            context.addMessage("registration-form", m);

            return "/register.jsf";
        }

        try {
            ut.begin();

            address.setPlace(place);
            customer.setAddress(address);
            em.persist(customer);

            ut.commit();

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

    private boolean isExistingCustomer(String email){
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByEmail", Customer.class);
        query.setParameter("email", email);

        return query.getSingleResult() != null;
    }
}
