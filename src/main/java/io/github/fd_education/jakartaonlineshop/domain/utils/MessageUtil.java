package io.github.fd_education.jakartaonlineshop.domain.utils;

import jakarta.faces.application.FacesMessage;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility class to wrap the faces message logic.
 *
 * @author Fabian Diemand
 */
public class MessageUtil {
    /**
     * Create a faces message with default severity (INFO).
     *
     * @param locale the locale for which to create the message
     * @param baseName the basename of the message ressource
     * @param key the key of the message in the ressource
     * @return a faces message containing the specified ressource text
     */
    public static FacesMessage getMessage(Locale locale, String baseName, String key){
        String msg = ResourceBundle.getBundle(baseName, locale).getString(key);
        return new FacesMessage(msg);
    }

    /**
     * Create a faces message with a specified severity
     *
     * @param locale the locale for which to create the message
     * @param baseName the basename of the message ressource
     * @param key the key of the message in the ressource
     * @param severity the severity for the message
     * @return a faces message containing the specified ressource text
     */
    public static FacesMessage getMessageWithSeverity(Locale locale, String baseName, String key, FacesMessage.Severity severity){
        String msg = ResourceBundle.getBundle(baseName, locale).getString(key);
        FacesMessage fm = new FacesMessage(msg);
        fm.setSeverity(severity);
        return fm;
    }
}
