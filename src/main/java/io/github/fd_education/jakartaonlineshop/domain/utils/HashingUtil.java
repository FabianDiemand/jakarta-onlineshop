package io.github.fd_education.jakartaonlineshop.domain.utils;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * Utility class to wrap the password hashing logic.
 *
 * @author Fabian Diemand
 */
public class HashingUtil {

    // Don't allow instantiation for utility class
    private HashingUtil(){}

    /**
     * Get a PBKDF2 hash from a string.
     *
     * @param value the value to be hashed
     * @return the PBKDF2 hash
     */
    public static String getHash(String value) {
        Pbkdf2PasswordEncoder enc = new Pbkdf2PasswordEncoder();
        return enc.encode(value);
    }

    /**
     * Check if a raw value matches a hashed value.
     *
     * @param raw the value to check
     * @param hash the hash to validate
     * @return true if the value matches the hash, false otherwise
     */
    public static boolean isMatchingHash(String raw, String hash){
        Pbkdf2PasswordEncoder enc = new Pbkdf2PasswordEncoder();
        return enc.matches(raw, hash);
    }
}
