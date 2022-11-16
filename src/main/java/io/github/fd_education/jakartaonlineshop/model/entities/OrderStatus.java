package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import jakarta.ws.rs.GET;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity @Table(schema = "ONLINESHOP", name = "ORDERSTATUS")
@Getter @Setter
public class OrderStatus {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private int statusId;

    private String name;

    private String description;

    @OneToMany(mappedBy = "orderStatusByOrderStatus")
    private Collection<Order> ordersByStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderStatus orderStatus = (OrderStatus) o;
        return statusId == orderStatus.statusId;
    }

    @Override
    public int hashCode() {
        int id = statusId;
        final int prime = 31;
        return prime + ((Integer) id).hashCode();
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "statusId=" + statusId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
