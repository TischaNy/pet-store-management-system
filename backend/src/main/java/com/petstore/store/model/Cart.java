package com.petstore.store.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
