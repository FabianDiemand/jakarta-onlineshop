package io.github.fd_education.jakartaonlineshop.model.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "quantity")
    private int quantity;
    @Basic
    @Column(name = "price")
    private int price;
    @Basic
    @Column(name = "img_uri")
    private String imgUri;
    @Basic
    @Column(name = "category")
    private int category;
    @Basic
    @Column(name = "brand")
    private int brand;
    @OneToMany(mappedBy = "productByProduct")
    private Collection<CartProduct> cartProductsByProductId;
    @OneToMany(mappedBy = "productByProduct")
    private Collection<OrderProduct> orderProductsByProductId;
    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "category_id", nullable = false)
    private Category categoryByCategory;
    @ManyToOne
    @JoinColumn(name = "brand", referencedColumnName = "brand_id", nullable = false)
    private Brand brandByBrand;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != product.productId) return false;
        if (quantity != product.quantity) return false;
        if (price != product.price) return false;
        if (category != product.category) return false;
        if (brand != product.brand) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (imgUri != null ? !imgUri.equals(product.imgUri) : product.imgUri != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + price;
        result = 31 * result + (imgUri != null ? imgUri.hashCode() : 0);
        result = 31 * result + category;
        result = 31 * result + brand;
        return result;
    }

    public Collection<CartProduct> getCartProductsByProductId() {
        return cartProductsByProductId;
    }

    public void setCartProductsByProductId(Collection<CartProduct> cartProductsByProductId) {
        this.cartProductsByProductId = cartProductsByProductId;
    }

    public Collection<OrderProduct> getOrderProductsByProductId() {
        return orderProductsByProductId;
    }

    public void setOrderProductsByProductId(Collection<OrderProduct> orderProductsByProductId) {
        this.orderProductsByProductId = orderProductsByProductId;
    }

    public Category getCategoryByCategory() {
        return categoryByCategory;
    }

    public void setCategoryByCategory(Category categoryByCategory) {
        this.categoryByCategory = categoryByCategory;
    }

    public Brand getBrandByBrand() {
        return brandByBrand;
    }

    public void setBrandByBrand(Brand brandByBrand) {
        this.brandByBrand = brandByBrand;
    }
}
