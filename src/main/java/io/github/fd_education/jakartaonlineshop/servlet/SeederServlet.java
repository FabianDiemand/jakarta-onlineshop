package io.github.fd_education.jakartaonlineshop.servlet;

import io.github.fd_education.jakartaonlineshop.model.entities.Address;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Place;
import io.github.fd_education.jakartaonlineshop.model.entities.Product;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.transaction.UserTransaction;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;
import java.util.logging.Logger;

public class SeederServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final static Logger log = Logger
            .getLogger(SeederServlet.class.toString());

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @Override
    public void init(){
        try{
            ut.begin();

            Customer johnDoe = createCustomer("John", "Doe", "john@doe.com", "Johndoe1!",
                    createAddress("DoeStreet", "1",
                            createPlace("1234", "DoePlace")));

            Customer janeDoe = createCustomer("Jane", "Doe", "jane@doe.com", "JaneDoe1!",
                    createAddress("DoeStreet", "3",
                            createPlace("1234", "DoePlace")));

            Customer timTom = createCustomer("Tim", "Tom", "tim@tom.com", "TimTom1!",
                    createAddress("TimStreet", "99",
                            createPlace("5678", "TimPlace")));

            Product graka = createProduct(
                    getImage(Objects.requireNonNull(getClass().getResource("/seed-images/graka.png"))),
                    "Grafikkarte",
                    "Tolles Teil. Nur einmal geschmolzen! 320 letzte Preis.",
                    320,
                    johnDoe);

            Product harddisk = createProduct(
                    getImage(Objects.requireNonNull(getClass().getResource("/seed-images/harddisk.png"))),
                    "Speicher-Dings",
                    "Reicht für alles. Fotos können sie behalten. Preis VB.",
                    200,
                    janeDoe);

            Product motherboard = createProduct(
                    getImage(Objects.requireNonNull(getClass().getResource("/seed-images/motherboard.png"))),
                    "Mainboard",
                    "Hat bis vor kurzem super funktioniert.",
                    100,
                    janeDoe);

            Product ram = createProduct(
                    getImage(Objects.requireNonNull(getClass().getResource("/seed-images/ram.png"))),
                    "RAM (nicht Auto!)",
                    "Fehlkauf... kein PS. Nie gebraucht! Neupreis, nicht verhandelbar",
                    999,
                    timTom);

            em.persist(johnDoe);
            em.persist(janeDoe);
            em.persist(timTom);
            em.persist(graka);
            em.persist(harddisk);
            em.persist(motherboard);
            em.persist(ram);

            ut.commit();

            log.info("Database Customer Seeded!");
        } catch(Exception e){
            log.severe("FAILED SEED: " + getClass().getResource("/seed-images/graka.png") + e);
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

    private Product createProduct(byte[] image, String name, String descr, double price, Customer customer){
        Product p = new Product();
        p.setImage(image);
        p.setName(name);
        p.setDescription(descr);
        p.setPrice(price);
        p.setSeller(customer);

        return p;
    }

    private byte[] getImage(URL path) throws IOException {
        File file = new File(path.getPath());
        return Files.readAllBytes(file.toPath());
    }

}
