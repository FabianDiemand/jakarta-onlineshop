package io.github.fd_education.jakartaonlineshop.model.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    private static Address a1;
    private static Address a2;

    @BeforeAll
    static void beforeAll() {
        a1 = new Address();
        a1.setId(1L);
    }

    @Test
    void equals_positive_test(){
        assertEquals(a1, a1);
    }

    @Test
    void equals_null_negative_test(){
        assertNotEquals(a1, a2);
    }

    @Test
    void equals_id_negative_test(){
        a2 = new Address();
        a2.setId(2L);
        assertNotEquals(a1, a2);
    }
}