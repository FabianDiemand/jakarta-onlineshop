package io.github.fd_education.jakartaonlineshop.domain.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocaleEnumTest {

    @Test
    void get_date_pattern_DE_test() {
        assertEquals("de", LocaleEnum.DE.getCode());
    }

    @Test
    void get_date_pattern_EN_test() {
        assertEquals("en", LocaleEnum.EN.getCode());
    }

    @Test
    void get_code_DE_test() {
        assertEquals("dd.MM.uuuu", LocaleEnum.DE.getDatePattern());
    }

    @Test
    void get_code_EN_test() {
        assertEquals("MM/dd/uuuu", LocaleEnum.EN.getDatePattern());
    }
}