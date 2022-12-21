package io.github.fd_education.jakartaonlineshop.domain.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderStatusTest {

    @Test
    void get_status_ordered_test() {
        assertEquals("ordered", OrderStatus.ORDERED.getStatus());
    }

    @Test
    void get_status_shipped_test() {
        assertEquals("shipped", OrderStatus.SHIPPED.getStatus());
    }

    @Test
    void get_status_delivered_test() {
        assertEquals("delivered", OrderStatus.DELIVERED.getStatus());
    }
}