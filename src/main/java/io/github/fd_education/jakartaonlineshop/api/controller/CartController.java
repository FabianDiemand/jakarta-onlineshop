package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.domain.pojo.Cart;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;

/**
 * Controller Bean to allow JSF facelets to interact with the shopping cart in session scope.
 *
 * @author Fabian Diemand
 */
@Named @SessionScoped
public class CartController implements Serializable {

    // Inject Cart POJO to handle interactions with the cart
    @Getter @Inject
    private Cart cart;
}
