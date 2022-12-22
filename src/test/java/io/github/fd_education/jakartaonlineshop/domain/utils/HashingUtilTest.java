package io.github.fd_education.jakartaonlineshop.domain.utils;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HashingUtilTest {
    private static final Pbkdf2PasswordEncoder enc = new Pbkdf2PasswordEncoder();

    @Test
    void get_hash_test() {
        String password = "testpassword";
        String hash = HashingUtil.getHash(password);

        assertTrue(enc.matches(password, hash));
    }

    @Test
    void is_matching_hash_test() {
        String password = "testpassword";
        String hash = HashingUtil.getHash(password);

        assertEquals(enc.matches(password, hash), HashingUtil.isMatchingHash(password, hash));
    }
}