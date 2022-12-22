package io.github.fd_education.jakartaonlineshop.model.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private static Order o1;
    private static Order o2;

    @BeforeAll
    static void beforeAll() {
        Product p1 = new Product();
        p1.setId(1L);
        p1.setPrice(100);

        Product p2 = new Product();
        p2.setId(2L);
        p2.setPrice(200);

        Set<Product> productSet = new LinkedHashSet<>();
        productSet.add(p1);
        productSet.add(p2);

        o1 = new Order();
        o1.setId(1L);
        o1.setProducts(productSet);
    }

    @Test
    void get_total_test() {
        assertEquals(300.0, o1.getTotal());
    }

    @Test
    void get_total_string_test() {
        assertEquals("300.00", o1.getTotalString());
    }

    @Test
    void equals_positive_test(){
        assertEquals(o1, o1);
    }

    @Test
    void equals_null_negative_test(){
        assertNotEquals(o1, o2);
    }

    @Test
    void equals_id_negative_test(){
        o2 = new Order();
        o2.setId(2L);
        assertNotEquals(o1, o2);
    }
}