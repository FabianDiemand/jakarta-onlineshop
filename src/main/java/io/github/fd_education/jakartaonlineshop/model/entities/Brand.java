package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(schema = "ONLINESHOP", name = "BRAND")
@Getter @Setter
public class Brand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private int brandId;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Brand brand = (Brand) o;
        return brandId == brand.brandId;
    }

    @Override
    public int hashCode() {
        int id = brandId;
        final int prime = 31;
        return prime + ((Integer) id).hashCode();
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandId=" + brandId +
                ", name='" + name + '\'' +
                '}';
    }
}
