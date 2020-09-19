package com.geekbrains.hibernate.krilov.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer_products")
public class Deal implements Serializable {

    @Id
    @Column(name = "customer_id")
    private Long customer_id;

    @Id
    @Column(name = "product_id")
    private Long product_id;

    @Column(name = "dealPrice")
    private double dealPrice;

    public Deal() {
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProducts_id(Long products_id) {
        this.product_id = products_id;
    }

    public double getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(double dealPrice) {
        this.dealPrice = dealPrice;
    }
}
