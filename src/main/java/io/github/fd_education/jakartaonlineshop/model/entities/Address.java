package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity @Table(schema = "ONLINESHOP", name = "ADDRESS")
@Getter @Setter
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;

    private String housenumber;

    @Column(name = "place")
    private int placeForeignKey;

    @ManyToOne
    private Place place;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;
        return addressId == address.addressId;
    }

    @Override
    public int hashCode() {
        int id = addressId;
        final int prime = 31;
        return prime + ((Integer) id).hashCode();
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", housenumber='" + housenumber + '\'' +
                ", place=" + place +
                '}';
    }
}
