package io.github.fd_education.jakartaonlineshop.api.servlet;

import io.github.fd_education.jakartaonlineshop.model.repository.ProductRepository;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * WebServlet to load a product image.
 *
 * @author Fabian Diemand
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {

    // Abstraction of the data layer with a repository
    @Inject
    private ProductRepository productRepository;

    /**
     * Get request to get the image from the database and send it to the client.
     *
     * @param req an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try{
            String id = req.getParameter("id");

            byte[] image = productRepository.getImageOfProductById(Long.parseLong(id));
            resp.reset();
            resp.getOutputStream().write(image);
        } catch(Exception ex){
            throw new ServletException(ex.getMessage());
        }
    }
}
