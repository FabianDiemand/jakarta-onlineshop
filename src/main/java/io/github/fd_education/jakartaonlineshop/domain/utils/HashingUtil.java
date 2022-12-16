package io.github.fd_education.jakartaonlineshop.domain.utils;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class HashingUtil {

    public static String getHash(String value) {
        Pbkdf2PasswordEncoder enc = new Pbkdf2PasswordEncoder();
        return enc.encode(value);
    }

    public static boolean isMatchingHash(String raw, String hash){
        Pbkdf2PasswordEncoder enc = new Pbkdf2PasswordEncoder();
        return enc.matches(raw, hash);
    }
}
