package io.github.fd_education.jakartaonlineshop.api.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    public void change(String lang) {
        this.lang = lang;
    }

    public String formatDate(LocalDate date){
        DateTimeFormatter dtf;

        if(Objects.equals(lang, "de")){
            dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        } else {
            dtf = DateTimeFormatter.ofPattern("MM/dd/uuuu");
        }
        return dtf.format(date);
    }
}
