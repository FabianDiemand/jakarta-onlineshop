package io.github.fd_education.jakartaonlineshop.model.entities;

import io.github.fd_education.jakartaonlineshop.utils.HashingUtil;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private static final long serialVersionUID = 1L;

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

    public void setPassword(String password){
        this.password = HashingUtil.getHash(password);
    }

    public void addToWishlist(Product product){
        wishlist.add(product);
    }

    public boolean wishlistContains(Product product){
        product.getWishedBy().add(this);
        return wishlist.contains(product);
    }

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