package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import jakarta.annotation.Resource;
import jakarta.el.ELContext;
import jakarta.el.ELResolver;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Named
@RequestScoped
public class BuyController implements Serializable {
    private static final long serialVersionUID = 1L;

    private final static Logger log = Logger
            .getLogger(BuyController.class.toString());

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    public String update(Long id) {
        FacesContext ctx = FacesContext
                .getCurrentInstance();
        ELContext elc = ctx.getELContext();
        ELResolver elr = ctx.getApplication()
                .getELResolver();
        LoginController loginController = (LoginController) elr
                .getValue(
                        elc,
                        null,
                        "signinController");

        Customer customer = loginController.getCustomer();
        try {
            ut.begin();
            Product product = em.find(Product.class, id);
            product.setBuyer(customer);
            product.setSold(LocalDateTime.now());
            ut.commit();
            log.info(product + " bought by " + customer);
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        return "/search.jsf";
    }
}
