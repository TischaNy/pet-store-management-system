package com.petstore.store.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;
    private String imageUrl;
    private String name;
    private Double price;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Product(){

    }

    public Product(long code, String imageUrl, String name, Double price, Category category){
        this.code = code;
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // getters and setters

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
