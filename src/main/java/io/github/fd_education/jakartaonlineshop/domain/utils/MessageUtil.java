package io.github.fd_education.jakartaonlineshop.domain.utils;

import jakarta.faces.application.FacesMessage;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtil {
    public static FacesMessage getMessage(Locale locale, String baseName, String key){
        String msg = ResourceBundle.getBundle(baseName, locale).getString(key);
        return new FacesMessage(msg);
    }

    public static FacesMessage getMessageWithSeverity(Locale locale, String baseName, String key, FacesMessage.Severity severity){
        String msg = ResourceBundle.getBundle(baseName, locale).getString(key);
        FacesMessage fm = new FacesMessage(msg);
        fm.setSeverity(severity);
        return fm;
    }
}
