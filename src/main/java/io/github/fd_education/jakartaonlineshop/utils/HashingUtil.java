package io.github.fd_education.jakartaonlineshop.utils;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.logging.Logger;

public class HashingUtil {

    public static String getHash(String value) {
        Pbkdf2PasswordEncoder enc = new Pbkdf2PasswordEncoder();
        return enc.encode(value);
    }
}
