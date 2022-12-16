package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity @Table(name = "\"order\"", schema = "onlineshop")
@NamedQueries(
        @NamedQuery(
                name = "Order.findByCustomer",
                query = "SELECT o FROM Order o WHERE o.customer = :customer"
        )
)
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Column(name = "ordered_at", nullable = false)
    private LocalDate orderedAt;

    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid = false;

    @Column(name = "paid_at")
    private LocalDate paidAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;

    @Lob
    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @ManyToMany
    @JoinTable(name = "order_product", schema = "onlineshop",
            joinColumns = @JoinColumn(name = "\"order\""),
            inverseJoinColumns = @JoinColumn(name = "product"))
    private Set<Product> products = new LinkedHashSet<>();

    @Transient
    @Getter(AccessLevel.NONE)
    private double total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Used in orderOverview facelet
    @SuppressWarnings("unused")
    public double getTotal() {
        total = products.stream().mapToDouble(Product::getPrice).sum();

        return total;
    }
}