package io.github.fd_education.jakartaonlineshop.api.servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try{
            String id = req.getParameter("id");

            Query query = em.createQuery("SELECT p.image FROM Product  p WHERE p.id = :id");
            query.setParameter("id", Long.parseLong(id));

            byte[] image = (byte[]) query.getSingleResult();
            resp.reset();
            resp.getOutputStream().write(image);
        } catch(Exception ex){
            throw new ServletException(ex.getMessage());
        }
    }
}
