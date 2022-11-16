package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity @Table(schema = "ONLINESHOP", name = "PLACE")
@Getter @Setter
public class Place {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private int placeId;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "place_name")
    private String placeName;

    @OneToMany(mappedBy = "place")
    private Collection<Address> addressesByPlace;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;
        return placeId == place.placeId;
    }

    @Override
    public int hashCode() {
        int id = placeId;
        final int prime = 31;
        return prime + ((Integer) id).hashCode();
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeId=" + placeId +
                ", postalCode='" + postalCode + '\'' +
                ", placeName='" + placeName + '\'' +
                '}';
    }
}
