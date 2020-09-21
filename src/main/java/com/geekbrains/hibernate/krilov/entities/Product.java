package com.geekbrains.hibernate.krilov.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price", precision=10, scale=2)
    private double price;

    @OneToMany(mappedBy = "product")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<Deal> deals;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    @Override
    public String toString() {
        return String.format("Product [id = %d, name = %s, price = %s]", id, name, price);
    }
}
