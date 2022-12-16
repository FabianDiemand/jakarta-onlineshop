package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.api.listeners.OrderListener;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Order;
import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import jakarta.annotation.Resource;
import jakarta.el.ELContext;
import jakarta.el.ELResolver;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.ArrayDataModel;
import jakarta.faces.model.DataModel;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Named @RequestScoped
@Getter @Setter
public class OrderController {

    private final static Logger log = Logger
            .getLogger(OrderController.class.toString());
    @Inject
    Order order;

    @Resource
    UserTransaction ut;

    @PersistenceContext
    EntityManager em;

    public void createOrderFromCart(Customer customer, Set<Product> cart) {

        FacesContext ctx = FacesContext.getCurrentInstance();
        ELContext elc = ctx.getELContext();
        ELResolver elr = ctx.getApplication().getELResolver();

        try {
            ut.begin();

            for(Product product: cart){
                Product p = em.find(Product.class, product.getId());

                p.setBuyer(customer);
                p.setSold(true);

                em.merge(p);
            }

            order.setOrderedAt(LocalDate.now());
            order.setCustomer(customer);
            order.setIsPaid(false);
            // TODO: Create ENUM for Order states
            order.setOrderStatus("ORDERED");
            order.setProducts(cart);

            em.persist(order);

            ut.commit();

            CartController cartController = (CartController) elr.getValue(elc,null,"cartController");
            cartController.emptyCart();

            ProfileController profileController = (ProfileController) elr.getValue(elc, null, "profileController");
            profileController.fetchCustomer();

        } catch (Exception exception) {
            log.info(exception.toString());
        }
    }

    public DataModel<Order> getOrdersByCustomer(Customer customer){
        List<Order> list = findByCustomer(customer);

        Order[] orders = list.toArray(new Order[0]);

        DataModel<Order> dataModel = new ArrayDataModel<>(orders);
        dataModel.addDataModelListener(new OrderListener());

        return dataModel;
    }

    public List<Order> findByCustomer(Customer customer){
        try{
            TypedQuery<Order> query = em.createNamedQuery("Order.findByCustomer", Order.class);
            query.setParameter("customer", customer);
            return query.getResultList();
        } catch(Exception e){
            log.severe(e.getMessage());
        }

        return new ArrayList<>();
    }
}
