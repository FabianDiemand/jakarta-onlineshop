package io.github.fd_education.jakartaonlineshop.model.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceTest {
    private static Place p1;
    private static Place p2;

    @BeforeAll
    static void beforeAll() {
        p1 = new Place();
        p1.setId(1L);
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
        p2 = new Place();
        p2.setId(2L);
        assertNotEquals(p1, p2);
    }
}