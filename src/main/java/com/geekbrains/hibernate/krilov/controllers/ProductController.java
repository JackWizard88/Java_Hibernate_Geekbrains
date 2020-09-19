package com.geekbrains.hibernate.krilov.controllers;

import com.geekbrains.hibernate.krilov.entities.Customer;
import com.geekbrains.hibernate.krilov.entities.Product;
import org.hibernate.Session;
import java.util.List;

public class ProductController {

    public static List<Product> getAllProducts(Session session) {
        session.beginTransaction();
        List<Product> products = session.createQuery("SELECT p FROM Product p").getResultList();
        session.getTransaction().commit();
        return products;
    }

    public static List<Customer> getCustomersListByID(Session session, Long id) {
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        List<Customer> customers = product.getCustomers();
        System.out.println(customers);              // тут тот же вопрос что и в покупателях
        session.getTransaction().commit();
        return customers;
    }
}
