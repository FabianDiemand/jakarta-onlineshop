package io.github.fd_education.jakartaonlineshop.model.entities;

import io.github.fd_education.jakartaonlineshop.utils.HashingUtil;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", unique = true)
    private Address address;

    @OneToMany(mappedBy = "seller")
    @ToString.Exclude
    private List<Product> offers;

    @OneToMany(mappedBy = "buyer")
    @ToString.Exclude
    private List<Product> orders;

    @Transient
    private boolean loggedIn = false;

    public void setPassword(String password){
        this.password = HashingUtil.getHash(password);
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