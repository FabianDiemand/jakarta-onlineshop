package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * JPA entity for Address data.
 * Has Many-to-one relation to Customer entity.
 *
 * @author Fabian Diemand
 */
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "address", schema = "onlineshop")
public class Address implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Long id;

    @Version
    private Long version;

    @Column(name = "streetname", nullable = false)
    private String streetname;

    @Column(name = "housenumber", nullable = false)
    private String housenumber;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "place", unique = true)
    private Place place;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}