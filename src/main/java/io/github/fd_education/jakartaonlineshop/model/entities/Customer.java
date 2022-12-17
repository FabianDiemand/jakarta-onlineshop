package io.github.fd_education.jakartaonlineshop.model.entities;

import io.github.fd_education.jakartaonlineshop.domain.utils.HashingUtil;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * JPA entity for Customer data.
 *
 * @author Fabian Diemand
 */
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "customer", schema = "onlineshop")
@NamedQueries(
        {
                @NamedQuery(
                        name = "Customer.findAll",
                        query = "SELECT c FROM Customer c"
                ),
                @NamedQuery(
                        name = "Customer.findByEmail",
                        query = "SELECT c FROM Customer c WHERE c.email = :email "
                )
        }
)
public class Customer implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Version
    private Long version;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private String password;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "address", unique = true)
    private Address address;

    @OneToMany(mappedBy = "seller")
    @ToString.Exclude
    private List<Product> offers;

    @OneToMany(mappedBy = "buyer")
    @ToString.Exclude
    private List<Product> orders;

    @ToString.Exclude
    @ManyToMany(targetEntity = Product.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "wishlist", schema = "onlineshop",
            joinColumns = @JoinColumn(name = "customer_fk", referencedColumnName = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_fk", referencedColumnName = "product_id"))
    private Set<Product> wishlist = new HashSet<>();

    @Transient
    private boolean loggedIn = false;

    /**
     * Custom setter for the password field to ensure that the password is hashed.
     *
     * @param password the password entered by the user
     */
    public void setPassword(String password){
        this.password = HashingUtil.getHash(password);
    }

    /**
     * Add a product to the wishlist of the customer.
     *
     * @param product the product to add to the wishlist
     */
    public void addToWishlist(Product product){
        wishlist.add(product);
    }

    /**
     * Check if the wishlist contains the product
     *
     * @param product the product to be looked up
     * @return true if the product is in the wishlist, false otherwise
     */
    public boolean wishlistContains(Product product){
        return wishlist.contains(product);
    }

    /**
     * Remove the product from the wishlist
     *
     * @param product the product to be removed
     */
    public void removeFromWishlist(Product product){
        wishlist.remove(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}