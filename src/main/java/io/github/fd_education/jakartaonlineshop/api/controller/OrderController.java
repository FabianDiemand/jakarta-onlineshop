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

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
@Getter
@Setter
public class OrderController {

    private final static Logger log = Logger
            .getLogger(OrderController.class.toString());
    @Inject
    Order order;

    @Inject
    OrderRepository orderRepository;

    @Inject
    ProductRepository productRepository;

    public void createOrderFromCart(Customer customer, Cart cart) {

        FacesContext ctx = FacesContext.getCurrentInstance();
        ELContext elc = ctx.getELContext();
        ELResolver elr = ctx.getApplication().getELResolver();

        for (Product product : cart.getProducts()) {
            Product p = productRepository.getById(product.getId());

            p.setBuyer(customer);
            p.setSold(true);

            productRepository.update(p);
        }

        order.setOrderedAt(LocalDate.now());
        order.setCustomer(customer);
        order.setIsPaid(false);
        order.setOrderStatus(OrderStatus.ORDERED.getStatus());
        order.setProducts(cart.getProducts());

        orderRepository.create(order);

        CartController cartController = (CartController) elr.getValue(elc, null, "cartController");
        cartController.emptyCart();

        ProfileController profileController = (ProfileController) elr.getValue(elc, null, "profileController");
        profileController.fetchCustomer();
    }

    public DataModel<Order> getOrdersByCustomer(Customer customer) {
        List<Order> list = orderRepository.getByCustomer(customer);

        Order[] orders = list.toArray(new Order[0]);

        DataModel<Order> dataModel = new ArrayDataModel<>(orders);
        dataModel.addDataModelListener(new OrderListener());

        return dataModel;
    }
}
