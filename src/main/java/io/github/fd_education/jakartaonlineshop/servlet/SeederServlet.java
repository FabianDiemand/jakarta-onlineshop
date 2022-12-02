package io.github.fd_education.jakartaonlineshop.servlet;

import io.github.fd_education.jakartaonlineshop.controller.SellController;
import io.github.fd_education.jakartaonlineshop.model.entities.Address;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Place;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.transaction.UserTransaction;

import java.util.logging.Logger;

@WebServlet("/seeder-servlet")
public class SeederServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final static Logger log = Logger
            .getLogger(SellController.class.toString());

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @Override
    public void init(){
        try{
            ut.begin();

            em.persist(createCustomer("John", "Doe", "john@doe.com", "Johndoe1!",
                    createAddress("DoeStreet", "1",
                            createPlace("1234", "DoePlace"))));

            em.persist(createCustomer("Jane", "Doe", "jane@doe.com", "JaneDoe1!",
                    createAddress("DoeStreet", "3",
                            createPlace("1234", "DoePlace"))));

            em.persist(createCustomer("Tim", "Tom", "tim@tom.com", "TimTom1!",
                    createAddress("TimStreet", "99",
                            createPlace("5678", "TimPlace"))));

            ut.commit();
            log.info("Database Customer Seeded!");
        } catch(Exception e){
            log.severe("Database Seeding failed!!!");
        }
    }

    private Place createPlace(String postalCode, String placeName){
        Place p = new Place();
        p.setPostalCode(postalCode);
        p.setPlaceName(placeName);

        return p;
    }

    private Address createAddress(String streetname, String housenumber, Place place){
        Address a = new Address();
        a.setStreetname(streetname);
        a.setHousenumber(housenumber);
        a.setPlace(place);

        return a;
    }

    private Customer createCustomer(String firstname, String lastname, String email, String password, Address address){
        Customer c = new Customer();
        c.setFirstName(firstname);
        c.setLastName(lastname);
        c.setEmail(email);
        c.setPassword(password);
        c.setAddress(address);

        return c;
    }

}
