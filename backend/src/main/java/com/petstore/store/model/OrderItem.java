package com.petstore.store.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;
    @Column(name = "amount")
    private double amount;
    @Column(name = "unitPrice")
    private double unitPrice;
    @Column(name = "quantity")
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId")
    private Cart cart;

    public OrderItem(){

    }

    public OrderItem(long id, Product product, double amount, double unitPrice, int quantity, Order order){
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.order = order;
    }

    public Double getTotalPrice(){
        Double total = getUnitPrice()*getQuantity();
        return total;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
