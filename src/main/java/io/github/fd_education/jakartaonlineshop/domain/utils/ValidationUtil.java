package io.github.fd_education.jakartaonlineshop.domain.utils;

import java.util.Objects;
import java.util.regex.Pattern;

public class ValidationUtil {

    // Don't allow instantiation for utility class
    private ValidationUtil(){}

    /**
     * Validate the email against a validation regex:
     * Start with letters or numbers, @-separation, dot-separated domains
     *
     * @param email the email address to validate
     * @return true is the email is valid, false otherwise
     */
    @SuppressWarnings("RegExpRedundantNestedCharacterClass")
    public static boolean isInvalidEmail(String email){
        if(email == null || email.isEmpty()) return true;

        String emailPattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        return !Pattern.matches(emailPattern, email);
    }

    /**
     * Validate the password against a validation regex:
     *         - at least one digit
     *         - at least one uppercase character
     *         - at least one lowercase character
     *         - at least one special character
     *
     * @param password the password to validate
     * @return true if the password is valid, false otherwise
     */
    @SuppressWarnings("RegExpRedundantNestedCharacterClass")
    public static boolean isWeakPasswort(String password){
        if(password == null) return true;

        String strongPasswordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$";
        return !Pattern.matches(strongPasswordPattern, password);
    }

    /**
     * Compare passwords for equality.
     *
     * @param password the password
     * @param value the value to compare to
     * @return true if the passwords are equal, false otherwise
     */
    public static boolean isMatchingPassword(String password, String value){
        if(password == null || password.isEmpty() || value == null || value.isEmpty()) return false;

        return Objects.equals(password, value);
    }
}
