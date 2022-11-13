package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Place {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "place_id")
    private int placeId;
    @Basic
    @Column(name = "postal_code")
    private String postalCode;
    @Basic
    @Column(name = "place_name")
    private String placeName;
    @OneToMany(mappedBy = "placeByPlace")
    private Collection<Address> addressesByPlaceId;

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (placeId != place.placeId) return false;
        if (postalCode != null ? !postalCode.equals(place.postalCode) : place.postalCode != null) return false;
        if (placeName != null ? !placeName.equals(place.placeName) : place.placeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = placeId;
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (placeName != null ? placeName.hashCode() : 0);
        return result;
    }

    public Collection<Address> getAddressesByPlaceId() {
        return addressesByPlaceId;
    }

    public void setAddressesByPlaceId(Collection<Address> addressesByPlaceId) {
        this.addressesByPlaceId = addressesByPlaceId;
    }
}
