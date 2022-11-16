package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(schema = "ONLINESHOP", name = "PRODUCT")
@Getter @Setter
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    private String name;

    private String description;

    private int quantity;

    private int price;

    @Column(name = "img_uri")
    private String imgUri;

    @Column(name = "category")
    private int categoryForeignKey;

    @Column(name = "brand")
    private int brandForeignKey;
    @OneToMany(mappedBy = "productsOfCart")
    private Collection<CartProduct> cartProductsByProductId;
    @OneToMany(mappedBy = "productsOfOrder")
    private Collection<OrderProduct> orderProductsByProductId;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "category_id", nullable = false)
    private Category  category;

    @ManyToOne
    @JoinColumn(name = "brand", referencedColumnName = "brand_id", nullable = false)
    private Brand brand;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        int id = productId;
        final int prime = 31;
        return prime + ((Integer) id).hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", imgUri='" + imgUri + '\'' +
                ", category=" + category +
                ", brand=" + brand +
                '}';
    }
}
