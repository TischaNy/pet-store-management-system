package com.petstore.store.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private long code;
    @Column(name = "imageUrl")
    private String imageUrl;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Double price;
    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;
    @ManyToOne
    @JoinColumn(name="petId")
    private Pet pet;

    public Product(){

    }

    public Product(long code, String imageUrl, String name, Double price, Category category, Pet pet){
        this.code = code;
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
        this.category = category;
        this.pet = pet;
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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
