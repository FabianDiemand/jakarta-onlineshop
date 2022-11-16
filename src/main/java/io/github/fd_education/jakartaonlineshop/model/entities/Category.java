package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(schema = "ONLINESHOP", name = "CATEGORY")
@Getter @Setter
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    private String name;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;
        return categoryId == category.categoryId;
    }

    @Override
    public int hashCode() {
        int id = categoryId;
        final int prime = 31;
        return prime + ((Integer) id).hashCode();
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
