package io.github.fd_education.jakartaonlineshop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "connectionTestServlet", value = "/connection-test-servlet")
public class ConnectionTestServlet extends HttpServlet {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private EntityManager em;

    public void init() throws ServletException {
        em = emf.createEntityManager();

        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean emOpen = em.isOpen();

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Connected:" + emOpen + "</h1>");
        out.println("</body></html>");

    }

    public void destroy(){
        em.close();
    }
}
