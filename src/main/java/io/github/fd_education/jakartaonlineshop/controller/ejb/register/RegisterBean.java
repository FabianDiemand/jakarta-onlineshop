package io.github.fd_education.jakartaonlineshop.controller.ejb.register;

import io.github.fd_education.jakartaonlineshop.model.entities.Address;
import io.github.fd_education.jakartaonlineshop.model.entities.Customer;
import io.github.fd_education.jakartaonlineshop.model.entities.Place;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class RegisterBean implements RegisterBeanLocal {
    @Inject
    Customer customer;

    @Inject
    Address address;

    @Inject
    Place place;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(String firstname, String lastname, String phone, String street, String houseNumber, String postalCode, String placeName, String email, String password) {
        place.setPostalCode(postalCode);
        place.setPlaceName(placeName);

        address.setStreetname(street);
        address.setHousenumber(houseNumber);
        address.setPlace(place);

        customer.setFirstName(firstname);
        customer.setLastName(lastname);
        customer.setPhoneNumber(phone);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setAddress(address);

        em.persist(customer);
        System.out.println("Customer " + customer + " persisted");
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Customer " + customer + " persisted");
    }

    private Place checkPlaceDuplicate(String postalCode, String placeName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Place> cq = cb.createQuery(Place.class);

        Root<Place> p = cq.from(Place.class);
        Path<Object> name = p.get("place_name");
        Path<Object> code = p.get("postal_code");

        Predicate pPlaceName = cb.equal(name, placeName);
        Predicate pPostalCode = cb.equal(code, postalCode);

        cq.select(p).where(pPlaceName).where(pPostalCode);

        TypedQuery<Place> typedQuery = em.createQuery(cq);

        return typedQuery.getSingleResult();
    }
}
