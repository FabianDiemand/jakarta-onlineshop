package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.domain.enums.LocaleEnum;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controller Bean for internationalization of JSF contents and formats.
 *
 * @author Fabian Diemand
 */
@Named @SessionScoped
public class LocaleController implements Serializable {
    @Getter
    private LocaleEnum lang = LocaleEnum.DE;

    /**
     * Set the language of the application to a locale.
     *
     * @param lang the locale to be set.
     */
    public void setLang(LocaleEnum lang) {
        this.lang = lang;
    }

    /**
     * Format a date according to the current locale
     *
     * @param date the date to be formatted
     * @return a string with the formatted date
     */
    public String formatDate(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(lang.getDatePattern());
        return dtf.format(date);
    }
}
