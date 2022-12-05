package io.github.fd_education.jakartaonlineshop.controller;

import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.Part;
import jakarta.transaction.UserTransaction;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@Named
@RequestScoped
public class SellController implements Serializable {
    private static final long serialVersionUID = 1L;

    public final static int MAX_IMAGE_LENGTH = 400;

    private final static Logger log = Logger
            .getLogger(SellController.class.toString());

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @Getter
    @Setter
    private Part part;

    @Inject
    @Getter
    @Setter
    private Product product;

    public String persist(LoginController loginController) {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        try {
            ut.begin();
            InputStream input = part.getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[10240];
            for (int length; (length = input.read(buffer)) > 0; ) {
                output.write(buffer, 0, length);
            }
            product.setImage(scale(output.toByteArray()));

            Customer customer = loginController.getCustomer();

            customer = em.find(Customer.class, customer.getId());

            product.setSeller(customer);
            em.persist(product);

            ut.commit();

            log.info("Offered item: " + product);

            FacesMessage m = new FacesMessage(bundle.getString("offer_success"));
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage("sell-form", m);
        } catch (Exception e) {
            log.severe(e.getMessage());
        }

        return "/sell.jsf?faces-redirect=true";
    }

    public byte[] scale(byte[] foto) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(foto);
        BufferedImage originalBufferedImage = ImageIO.read(byteArrayInputStream);

        double originalWidth = originalBufferedImage.getWidth();
        double originalHeight = originalBufferedImage.getHeight();
        double relevantLength = Math.max(originalWidth, originalHeight);

        double transformationScale = MAX_IMAGE_LENGTH / relevantLength;
        int width = (int) Math.round(transformationScale * originalWidth);
        int height = (int) Math.round(transformationScale * originalHeight);

        BufferedImage resizedBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedBufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        AffineTransform affineTransform = AffineTransform.getScaleInstance(transformationScale, transformationScale);
        g2d.drawRenderedImage(originalBufferedImage, affineTransform);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedBufferedImage, "PNG", baos);
        return baos.toByteArray();
    }
}
