package com.geekbrains.hibernate.krilov.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "deals")
public class Deal implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn (name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn (name="product_id")
    private Product product;

    @Column(name = "dealPrice")
    private double dealPrice;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Deal() {
    }

    public double getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(double dealPrice) {
        this.dealPrice = dealPrice;
    }
}
