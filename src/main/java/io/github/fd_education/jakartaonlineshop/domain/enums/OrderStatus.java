package io.github.fd_education.jakartaonlineshop.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum contains order status.
 */
@AllArgsConstructor
public enum OrderStatus {
    ORDERED("ordered"),
    SHIPPED("shipped"),
    DELIVERED("delivered");

    @Getter
    final String status;
}
