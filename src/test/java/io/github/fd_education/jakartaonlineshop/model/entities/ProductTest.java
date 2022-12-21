package io.github.fd_education.jakartaonlineshop.model.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private static Product p1;
    private static Product p2;

    @BeforeAll
    static void beforeAll() {
        p1 = new Product();
        p1.setId(1L);
        p1.setPrice(100);
    }

    @Test
    void get_price_string_test() {
        assertEquals("100.00", p1.getPriceString());
    }

    @Test
    void equals_positive_test(){
        assertEquals(p1, p1);
    }

    @Test
    void equals_null_negative_test(){
        assertNotEquals(p1, p2);
    }

    @Test
    void equals_id_negative_test(){
        p2 = new Product();
        p2.setId(2L);
        assertNotEquals(p1, p2);
    }
}