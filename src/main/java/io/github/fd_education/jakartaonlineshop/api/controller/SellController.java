package io.github.fd_education.jakartaonlineshop.api.controller;

import io.github.fd_education.jakartaonlineshop.domain.utils.MessageUtil;
import io.github.fd_education.jakartaonlineshop.domain.utils.ScaleUtil;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import io.github.fd_education.jakartaonlineshop.model.repository.CustomerRepository;
import io.github.fd_education.jakartaonlineshop.model.repository.ProductRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Controller Bean to manage the selling process.
 *
 * @author Fabian Diemand
 */
@Named
@RequestScoped
public class SellController implements Serializable {

    private final static Logger log = Logger.getLogger(SellController.class.toString());

    // The length of the image
    private final static int MAX_IMAGE_LENGTH = 400;

    // Part object for the product image
    @Getter
    @Setter
    private Part part;

    // Product object to hold all product data
    @Inject
    @Getter
    @Setter
    private Product product;

    // Abstraction of the data layer with a repository
    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private ProductRepository productRepository;

    /**
     * Persist a product offered by a customer.
     *
     * @param customer the customer that offers the product
     * @return outcome of sell facelet with empty form
     */
    @SuppressWarnings("SameReturnValue")
    public String persist(Customer customer) {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();

        try {
            // Scale the product image
            InputStream input = part.getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[10240];
            for (int length; (length = input.read(buffer)) > 0; ) {
                output.write(buffer, 0, length);
            }
            product.setImage(ScaleUtil.scale(output.toByteArray(), MAX_IMAGE_LENGTH));

            // Make sure to have the latest version of the customer and persist the product
            Customer c = customerRepository.getById(customer.getId());
            product.setSeller(c);
            productRepository.create(product);
            log.info("Offered item: " + product);

            FacesMessage m = MessageUtil.getMessage(locale, "messages", "offer_success");
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage("sell-form", m);
        } catch (Exception e) {
            log.severe(e.getMessage());
        }

        return "/sell.jsf?faces-redirect=true";
    }
}
