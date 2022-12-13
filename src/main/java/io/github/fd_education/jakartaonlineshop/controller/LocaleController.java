package io.github.fd_education.jakartaonlineshop.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Objects;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class LocaleController implements Serializable {
    private static final long serialVersionUID = 1L;

    private String lang = "de";

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String change(String lang) {
        this.lang = lang;
        return "/index.jsf";
    }

    public String formatDate(LocalDate date){
        if(Objects.equals(lang, "de")){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");
            return dtf.format(date);
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/uuuu");
            return dtf.format(date);
        }
    }
}
