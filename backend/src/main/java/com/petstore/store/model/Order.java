package com.petstore.store.model;

import java.util.Date;

public class Order {
    private long id;
    private double amount;
    private Date orderDate;
    private Address customerAddress;
    private User customer;
}
