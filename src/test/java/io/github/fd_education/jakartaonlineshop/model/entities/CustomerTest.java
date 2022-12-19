package io.github.fd_education.jakartaonlineshop.model.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private static Product p;
    private static Customer c1;
    private static Customer c2;

    private static Pbkdf2PasswordEncoder enc;

    @BeforeAll
    static void beforeAll() {
        p = new Product();
        p.setId(1L);

        enc = new Pbkdf2PasswordEncoder();
    }

    @BeforeEach
    void beforeEach(){
        c1 = new Customer();
        c1.setId(1L);
    }

    @Test
    void set_password_test() {
        String password = "testpassword";
        c1.setPassword(password);
        assertTrue(enc.matches(password, c1.getPassword()));
    }

    @Test
    void add_to_wishlist_test() {
        c1.addToWishlist(p);
        assertFalse(c1.getWishlist().isEmpty());
        assertTrue(c1.getWishlist().contains(p));
    }

    @Test
    void wishlist_contains_test() {
        c1.addToWishlist(p);
        assertTrue(c1.wishlistContains(p));
    }

    @Test
    void remove_from_wishlist_test() {
        c1.addToWishlist(p);

        c1.removeFromWishlist(p);
        assertFalse(c1.getWishlist().contains(p));
        assertTrue(c1.getWishlist().isEmpty());
    }

    @Test
    void equals_positive_test(){
        assertEquals(c1, c1);
    }

    @Test
    void equals_null_negative_test(){
        assertNotEquals(c1, c2);
    }

    @Test
    void equals_id_negative_test(){
        c2 = new Customer();
        c2.setId(2L);
        assertNotEquals(c1, c2);
    }
}