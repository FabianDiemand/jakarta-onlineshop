package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * JPA entity for Product data.
 *
 * @author Fabian Diemand
 */
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "product", schema = "onlineshop")
@NamedQuery(
        name = "Product.findAllAvailable",
        query = "SELECT p from Product p WHERE p.sold = false"
)
public class Product implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Version
    private Long version;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "image", nullable = false)
    @Lob @Basic(fetch = FetchType.LAZY)
    @ToString.Exclude
    private byte[] image;

    @Column(name = "sold")
    private boolean sold = false;

    @ManyToOne
    private Customer seller;

    @ManyToOne
    private Customer buyer;

    @ManyToMany(mappedBy = "wishlist", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Customer> wishedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}