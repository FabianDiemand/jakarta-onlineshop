package io.github.fd_education.jakartaonlineshop.domain.pojo;

import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    private static Cart cart;

    private static Product p1;
    private static Product p2;
    private static Product p3;

    @BeforeAll
    static void setup() {
        p1 = new Product();
        p1.setId(1L);
        p1.setName("Product 1");
        p1.setPrice(100);
        p1.setDescription("Product 1 Description");

        p2 = new Product();
        p2.setId(2L);
        p2.setName("Product 2");
        p2.setPrice(200);
        p2.setDescription("Product 2 Description");

        p3 = new Product();
        p3.setId(3L);
        p3.setName("Product 3");
        p3.setPrice(300);
        p3.setDescription("Product 3 Description");
    }

    @BeforeEach
    void beforeEach() {
        cart = new Cart();
    }

    @Test
    void add_product_test() {
        cart.addProduct(p1);

        assertTrue(cart.getProducts().contains(p1));
    }

    @Test
    void remove_contained_product_test() {
        cart.addProduct(p1);
        cart.removeProduct(p1);

        assertTrue(cart.isEmpty());
    }

    @Test
    void remove_not_contained_product_test() {
        assertDoesNotThrow(() -> cart.removeProduct(p2));
    }

    @Test
    void contains_product_positive_test() {
        cart.addProduct(p1);
        assertTrue(cart.containsProduct(p1));
    }

    @Test
    void contains_product_negative_test() {
        assertFalse(cart.containsProduct(p1));
        assertFalse(cart.containsProduct(p2));
        assertFalse(cart.containsProduct(p3));
    }

    @Test
    void is_empty_positive_test() {
        assertTrue(cart.isEmpty());
    }

    @Test
    void is_empty_negative_test() {
        cart.addProduct(p1);
        assertFalse(cart.isEmpty());
    }

    @Test
    void clear_test() {
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.addProduct(p3);

        cart.clear();
        assertTrue(cart.isEmpty());
    }

    @Test
    void get_sum_test() {
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.addProduct(p3);

        assertEquals(600, cart.getSum());
    }

    @Test
    void get_sum_string_test() {
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.addProduct(p3);

        assertEquals("600.00", cart.getSumString());
    }
}