package com.geekbrains.hibernate.krilov.controllers;

import com.geekbrains.hibernate.krilov.entities.Customer;
import com.geekbrains.hibernate.krilov.entities.Deal;
import com.geekbrains.hibernate.krilov.entities.Product;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ProductController {

    public static List<Product> getAllProducts(Session session) {
        session.beginTransaction();
        List<Product> products = session.createQuery("SELECT p FROM Product p").getResultList();
        session.getTransaction().commit();
        return products;
    }

    public static Product getProductByID(Session session, Long id) {
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        return product;
    }

    public static void removeProductById(Session session, Long productId) {
        session.beginTransaction();
        session.createQuery("DELETE FROM Deal d WHERE d.product.id = " + productId).executeUpdate();
        session.createQuery("DELETE FROM Product p WHERE p.id = " + productId).executeUpdate();
        session.getTransaction().commit();
    }

    public static List<Customer> getCustomersListByID(Session session, Long id) {
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        List<Deal> deals = product.getDeals();
        List<Customer> customers = new ArrayList<>();
        for (Deal d: deals) {
            customers.add(session.get(Customer.class, d.getCustomer().getId()));
        }
        session.getTransaction().commit();
        return customers;
    }
}
