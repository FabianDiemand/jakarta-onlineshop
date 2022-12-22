package io.github.fd_education.jakartaonlineshop.domain.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationUtilTest {

    @Test
    void is_invalid_email_positive_test() {
        String invalidEmail1 = "@mail.com";
        String invalidEmail2 = "mail";
        String invalidEmail3 = "t@mail";
        String invalidEmail4 = ".com";

        assertTrue(ValidationUtil.isInvalidEmail(invalidEmail1));
        assertTrue(ValidationUtil.isInvalidEmail(invalidEmail2));
        assertTrue(ValidationUtil.isInvalidEmail(invalidEmail3));
        assertTrue(ValidationUtil.isInvalidEmail(invalidEmail4));
    }

    @Test
    void is_invalid_email_negative_test() {
        String validPassword = "test12@mail.com";

        assertFalse(ValidationUtil.isInvalidEmail(validPassword));
    }

    @Test
    void is_weak_passwort_positive_test() {
        String missingDigit = "Abcdefgh!!";
        String noUppercase = "abcdefgh4!!";
        String noLowercase = "ABCDEFGH4!!";
        String noSpecialChar = "Abcdefgh4";
        String notEightChars = "Abcde4!";

        assertTrue(ValidationUtil.isWeakPasswort(missingDigit));
        assertTrue(ValidationUtil.isWeakPasswort(noUppercase));
        assertTrue(ValidationUtil.isWeakPasswort(noLowercase));
        assertTrue(ValidationUtil.isWeakPasswort(noSpecialChar));
        assertTrue(ValidationUtil.isWeakPasswort(notEightChars));
    }

    @Test
    void is_weak_passwort_negative_test() {
        String strongPassword = "Abcdefgh4!!";
        assertFalse(ValidationUtil.isWeakPasswort(strongPassword));
    }

    @Test
    void is_matching_password_positive_test() {
        String password = "Abcdefgh4!!";
        String value = "Abcdefgh4!!";

        assertTrue(ValidationUtil.isMatchingPassword(password, value));
    }

    @Test
    void is_matching_password_negative_test() {
        String password = "Abcdefgh4!!";
        String value = "abcdefgh4!!";

        assertFalse(ValidationUtil.isMatchingPassword(password, value));
    }
}
