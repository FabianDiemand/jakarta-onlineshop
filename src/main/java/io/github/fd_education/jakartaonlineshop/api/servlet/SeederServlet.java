package io.github.fd_education.jakartaonlineshop.api.servlet;

import io.github.fd_education.jakartaonlineshop.domain.enums.OrderStatus;
import io.github.fd_education.jakartaonlineshop.model.entities.*;
import io.github.fd_education.jakartaonlineshop.model.repository.CustomerRepository;
import io.github.fd_education.jakartaonlineshop.model.repository.OrderRepository;
import io.github.fd_education.jakartaonlineshop.model.repository.ProductRepository;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

/**
 * Servlet to seed database at application startup (configured in web.xml)
 *
 * @author Fabian Diemand
 */
public class SeederServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(SeederServlet.class.toString());

    // Abstraction of the data layer with repositories
    @Inject
    private ProductRepository productRepository;
    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private OrderRepository orderRepository;

    /**
     * Create a collection of customers and products to seed the database.
     */
    @Override
    public void init(){

        Collection<Customer> customers = new ArrayList<>();
        Collection<Product> products = new ArrayList<>();
        Collection<Order> orders = new ArrayList<>();

        try{
            Customer johnDoe = createCustomer("John", "Doe", "john@doe.com", "Johndoe1!",
                    createAddress("DoeStreet", "1",
                            createPlace("1234", "DoePlace")));

            Customer janeDoe = createCustomer("Jane", "Doe", "jane@doe.com", "Janedoe1!",
                    createAddress("DoeStreet", "3",
                            createPlace("1234", "DoePlace")));

            Customer timTom = createCustomer("Tim", "Tom", "tim@tom.com", "Timtom1!",
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

            Product keyboard = createSoldProduct(
                    getImage(Objects.requireNonNull(getClass().getResource("/seed-images/tastatur.png"))),
                    "Tastatur",
                    "QWERTY Tastatur, Neuwertig, kann nur blau.",
                    333,
                    timTom,
                    johnDoe);

            Product headset = createSoldProduct(
                    getImage(Objects.requireNonNull(getClass().getResource("/seed-images/headset.png"))),
                    "Kopfhörer",
                    "Logitech Kopfhörer Pro X, Kabel fehlt!!! :(",
                    4,
                    janeDoe,
                    johnDoe);

            Product screen = createSoldProduct(
                    getImage(Objects.requireNonNull(getClass().getResource("/seed-images/screen.png"))),
                    "Bildschirm",
                    "Vorgestern gekauft. Fahrrad nicht im Lieferumfang enthalten.",
                    600,
                    janeDoe,
                    johnDoe);

            Order o1 = createPaidOrder(
                    johnDoe,
                    Set.of(headset),
                    OrderStatus.DELIVERED,
                    LocalDate.now().minusDays(30));

            Order o2 = createPaidOrder(
                    johnDoe,
                    Set.of(screen),
                    OrderStatus.SHIPPED,
                    LocalDate.now().minusDays(3));

            Order o3 = createUnpaidOrder(
                    johnDoe,
                    Set.of(keyboard));

            customers.add(johnDoe);
            customers.add(janeDoe);
            customers.add(timTom);

            products.add(graka);
            products.add(harddisk);
            products.add(motherboard);
            products.add(ram);
            products.add(keyboard);
            products.add(headset);
            products.add(screen);

            orders.add(o1);
            orders.add(o2);
            orders.add(o3);

            customerRepository.createMany(customers);
            productRepository.createMany(products);
            orderRepository.createMany(orders);

            log.info("Database Customer Seeded!");
        } catch(Exception e){
            log.severe("FAILED SEED: " + e + "\n Trace:" + Arrays.toString(e.getStackTrace()));
        }
    }

    // Helper method to create place entities
    private Place createPlace(String postalCode, String placeName){
        Place p = new Place();
        p.setPostalCode(postalCode);
        p.setPlaceName(placeName);

        return p;
    }

    // Helper method to create address entities
    private Address createAddress(String streetname, String housenumber, Place place){
        Address a = new Address();
        a.setStreetname(streetname);
        a.setHousenumber(housenumber);
        a.setPlace(place);

        return a;
    }

    // Helper method to create customer entities
    private Customer createCustomer(String firstname, String lastname, String email, String password, Address address){
        Customer c = new Customer();
        c.setFirstName(firstname);
        c.setLastName(lastname);
        c.setEmail(email);
        c.setPassword(password);
        c.setAddress(address);

        return c;
    }

    // Helper method to create product entities
    private Product createProduct(byte[] image, String name, String descr, double price, Customer seller){
        Product p = new Product();
        p.setImage(image);
        p.setName(name);
        p.setDescription(descr);
        p.setPrice(price);
        p.setSeller(seller);

        return p;
    }

    // Helper method to create sold product entities
    private Product createSoldProduct(byte[] image, String name, String descr, double price, Customer seller, Customer buyer){
        Product p = new Product();
        p.setImage(image);
        p.setName(name);
        p.setDescription(descr);
        p.setPrice(price);
        p.setSeller(seller);
        p.setSold(true);
        p.setBuyer(buyer);

        return p;
    }

    // Helper method to create unpaid order entities
    private Order createUnpaidOrder(Customer customer, Set<Product> products){
        Order o = new Order();
        o.setOrderedAt(LocalDate.now());
        o.setCustomer(customer);
        o.setProducts(products);
        o.setOrderStatus(OrderStatus.ORDERED.getStatus());
        return o;
    }

    // Helper method to create paid order entities
    private Order createPaidOrder(Customer customer, Set<Product> products, OrderStatus orderStatus, LocalDate payDate){
        Order o = new Order();
        o.setOrderedAt(LocalDate.now());
        o.setCustomer(customer);
        o.setProducts(products);
        o.setOrderStatus(orderStatus.getStatus());
        o.setIsPaid(true);
        o.setPaidAt(payDate);
        return o;
    }

    // Fetch image from directory specified with the url
    private byte[] getImage(URL path) throws IOException {
        File file = new File(path.getPath());
        return Files.readAllBytes(file.toPath());
    }

}
