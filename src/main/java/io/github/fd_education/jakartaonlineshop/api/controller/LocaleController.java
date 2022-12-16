package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.domain.enums.LocaleEnum;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Named
@SessionScoped
public class LocaleController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    private LocaleEnum lang = LocaleEnum.DE;

    public void setLang(LocaleEnum lang) {
        this.lang = lang;
    }

    public void change(LocaleEnum lang) {
        this.lang = lang;
    }

    public String formatDate(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(lang.getDatePattern());
        return dtf.format(date);
    }
}
