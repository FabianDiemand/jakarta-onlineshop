package io.github.fd_education.jakartaonlineshop.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum contains Language Codes according to ISO-639-1 and date formats.
 */
@AllArgsConstructor
public enum LocaleEnum {
    DE("de", "dd.MM.uuuu"),
    EN("en", "MM/dd/uuuu");

    @Getter
    private final String code;

    @Getter
    private final String datePattern;
}
