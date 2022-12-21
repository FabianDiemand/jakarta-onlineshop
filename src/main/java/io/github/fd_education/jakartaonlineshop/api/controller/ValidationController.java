package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.domain.utils.MessageUtil;
import io.github.fd_education.jakartaonlineshop.domain.utils.ValidationUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

import java.io.Serializable;

/**
 * Controller Bean for forms validation logic.
 * Methods separated per field to ease the extension with further validation per field if necessary.
 *
 * @author Fabian Diemand
 */
@Named @RequestScoped
public class ValidationController implements Serializable {

    /**
     * Validate that the first name is not empty.
     *
     * @param fc state information of the request facelet
     * @param ignored_ the component whose content to validate (not used)
     * @param obj the content to validate
     */
    public void isFirstName(FacesContext fc, @SuppressWarnings("unused") UIComponent ignored_, Object obj) {
        String value = (String) obj;

        if(value.isEmpty()){
            FacesMessage fm = MessageUtil.getMessageWithSeverity(fc.getViewRoot().getLocale(), "messages", "firstname_empty", FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    /**
     * Validate that the last name is not empty.
     *
     * @param fc state information of the request facelet
     * @param ignored_ the component whose content to validate (not used)
     * @param obj the content to validate
     */
    public void isLastName(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) {
        String value = (String) obj;

        if(value.isEmpty()){
            FacesMessage fm = MessageUtil.getMessageWithSeverity(fc.getViewRoot().getLocale(), "messages", "lastname_empty", FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    /**
     * Validate that the street name is not empty.
     *
     * @param fc state information of the request facelet
     * @param ignored_ the component whose content to validate (not used)
     * @param obj the content to validate
     */
    public void isStreet(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) {
        String value = (String) obj;

        if(value.isEmpty()){
            FacesMessage fm = MessageUtil.getMessageWithSeverity(fc.getViewRoot().getLocale(), "messages", "street_empty", FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    /**
     * Validate that the house number is not empty.
     *
     * @param fc state information of the request facelet
     * @param ignored_ the component whose content to validate (not used)
     * @param obj the content to validate
     */
    public void isHouseNumber(FacesContext fc, @SuppressWarnings("unused") UIComponent ignored_, Object obj) {
        String value = (String) obj;

        if(value.isEmpty()){
            FacesMessage fm = MessageUtil.getMessageWithSeverity(fc.getViewRoot().getLocale(), "messages", "house_number_empty", FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    /**
     * Validate that the postal code is not empty.
     *
     * @param fc state information of the request facelet
     * @param ignored_ the component whose content to validate (not used)
     * @param obj the content to validate
     */
    public void isPostalCode(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) {
        String value = (String) obj;

        if(value.isEmpty()){
            FacesMessage fm = MessageUtil.getMessageWithSeverity(fc.getViewRoot().getLocale(), "messages", "postal_code_empty", FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    /**
     * Validate that the place name is not empty.
     *
     * @param fc state information of the request facelet
     * @param ignored_ the component whose content to validate (not used)
     * @param obj the content to validate
     */
    public void isPlace(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) {
        String value = (String) obj;

        if(value.isEmpty()){
            FacesMessage fm = MessageUtil.getMessageWithSeverity(fc.getViewRoot().getLocale(), "messages", "place_empty", FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    /**
     * Validate that the email is valid and not empty.
     *
     * @param fc state information of the request facelet
     * @param ignored_ the component whose content to validate (not used)
     * @param obj the content to validate
     */
    public void isEmail(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) throws ValidatorException {
        String value = (String) obj;

        if(value.isEmpty()){
            FacesMessage fm = MessageUtil.getMessageWithSeverity(fc.getViewRoot().getLocale(), "messages", "email_empty", FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }

        if(ValidationUtil.isInvalidEmail(value)){
            FacesMessage fm = MessageUtil.getMessageWithSeverity(fc.getViewRoot().getLocale(), "messages", "email_invalid", FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    /**
     * Validate that the password is not empty matches the constraints.
     * Constraints:
     *   - at least one digit
     *   - at least one uppercase character
     *   - at least one lowercase character
     *   - at least one special character
     *   - a length of 8 to 20 characters
     *
     * @param fc state information of the request facelet
     * @param ignored_ the component whose content to validate (not used)
     * @param obj the content to validate
     */
    public void isStrongPassword(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) throws ValidatorException {
        String value = (String) obj;

        if(value.isEmpty()){
            FacesMessage fm = MessageUtil.getMessageWithSeverity(fc.getViewRoot().getLocale(), "messages", "password_empty", FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }

        if(ValidationUtil.isWeakPasswort(value)){
            FacesMessage fm = MessageUtil.getMessageWithSeverity(fc.getViewRoot().getLocale(), "messages", "password_weak", FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    /**
     * Validate that the password is not matches the constraints. Required when setting a new password.
     * Constraints:
     *   - at least one digit
     *   - at least one uppercase character
     *   - at least one lowercase character
     *   - at least one special character
     *   - a length of 8 to 20 characters
     *
     * @param fc state information of the request facelet
     * @param ignored_ the component whose content to validate (not used)
     * @param obj the content to validate
     */
    public void isStrongNewPasswort(FacesContext fc,@SuppressWarnings("unused") UIComponent ignored_, Object obj) throws ValidatorException{
        String value = (String) obj;
        if(value.isEmpty()) return;

        if(ValidationUtil.isWeakPasswort(value)){
            FacesMessage fm = MessageUtil.getMessageWithSeverity(fc.getViewRoot().getLocale(), "messages", "password_weak", FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }

    /**
     * Validate that the password and the verification password match.
     *
     * @param fc state information of the request facelet
     * @param uic the component whose content to validate
     * @param obj the content to validate
     */
    public void isMatchingPassword(FacesContext fc, UIComponent uic, Object obj) {
        String password = (String) obj;

        // get the password from the bound attribute
        String verificationPassword = (String) uic.getAttributes().get("password");
        if(verificationPassword.isEmpty()) return;

        if(!ValidationUtil.isMatchingPassword(password, verificationPassword)){
            FacesMessage fm = MessageUtil.getMessageWithSeverity(fc.getViewRoot().getLocale(), "messages", "passwords_not_matching", FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(fm);
        }
    }
}
