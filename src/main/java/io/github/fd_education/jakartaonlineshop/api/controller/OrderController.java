package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.api.listeners.OrderListener;
import io.github.fd_education.jakartaonlineshop.domain.enums.OrderStatus;
import io.github.fd_education.jakartaonlineshop.domain.pojo.Cart;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Order;
import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import io.github.fd_education.jakartaonlineshop.model.repository.OrderRepository;
import io.github.fd_education.jakartaonlineshop.model.repository.ProductRepository;
import jakarta.el.ELContext;
import jakarta.el.ELResolver;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.ArrayDataModel;
import jakarta.faces.model.DataModel;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

/**
 * Controller Bean to manage orders.
 *
 * @author Fabian Diemand
 */
@Named @RequestScoped
@Getter @Setter
public class OrderController implements Serializable {
    private final static Logger log = Logger.getLogger(OrderController.class.toString());

    // Order object to hold all order data
    @Inject
    private Order order;

    // Abstraction of the data layer with repositories
    @Inject
    private OrderRepository orderRepository;
    @Inject
    private ProductRepository productRepository;

    /**
     * Create an order containing the products in the cart.
     *
     * @param customer the customer for whom to create the order
     * @param cart the cart to create an order from
     */
    public void createOrderFromCart(Customer customer, Cart cart) {

        // Change the buyer and sold state of all products in the cart
        for (Product product : cart.getProducts()) {
            Product p = productRepository.getById(product.getId());

            p.setBuyer(customer);
            p.setSold(true);

            productRepository.update(p);
        }

        // Set the data of the order and persist it
        order.setOrderedAt(LocalDate.now());
        order.setCustomer(customer);
        order.setIsPaid(false);
        order.setOrderStatus(OrderStatus.ORDERED.getStatus());
        order.setProducts(cart.getProducts());
        orderRepository.create(order);

        ELContext elc = FacesContext.getCurrentInstance().getELContext();
        ELResolver elr = elc.getELResolver();

        // Clear the cart
        CartController cartController = (CartController) elr.getValue(elc, null, "cartController");
        cartController.getCart().clear();

        // Update the customer in the profile
        ProfileController profileController = (ProfileController) elr.getValue(elc, null, "profileController");
        profileController.fetchCustomer();
    }

    /**
     * Get an observable list of orders from a customer.
     *
     * @param customer the customer whose orders must be returned
     * @return observable list of orders
     */
    public DataModel<Order> getOrdersByCustomer(Customer customer) {
        List<Order> list = orderRepository.getByCustomer(customer);

        Order[] orders = list.toArray(new Order[0]);

        DataModel<Order> dataModel = new ArrayDataModel<>(orders);
        dataModel.addDataModelListener(new OrderListener());

        return dataModel;
    }
}
