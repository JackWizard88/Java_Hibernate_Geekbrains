package com.geekbrains.hibernate.krilov.controllers;

import com.geekbrains.hibernate.krilov.entities.Customer;
import com.geekbrains.hibernate.krilov.entities.Deal;
import com.geekbrains.hibernate.krilov.entities.Product;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    public static List<Customer> getAllCustomers(Session session) {
        session.beginTransaction();
        List<Customer> customers = session.createQuery("SELECT c FROM Customer c").getResultList();
        session.getTransaction().commit();
        return customers;
    }

    public static Customer getCustomerByID(Session session, Long id) {
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.getTransaction().commit();
        return customer;
    }

    public static List<Product> getProductListByID(Session session, Long id) {
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        List<Deal> deals = customer.getDeals();
        List<Product> products = new ArrayList<>();
        for (Deal d: deals) {
            products.add(session.get(Product.class, d.getProduct().getId()));
        }
        session.getTransaction().commit();
        return products;
    }

    public static void removeProductFromCart(Session session, Long productId, Long customerId) {
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);

        //не удаляется из таблицы сделок
        customer.getDeals().removeIf(p -> p.getProduct().getId().equals(productId));
        session.getTransaction().commit();
    }

    public static void removeCustomerById(Session session, Long customerId) {
        session.beginTransaction();
        session.createQuery("DELETE FROM Deal d WHERE d.customer.id = " + customerId).executeUpdate();
        session.createQuery("DELETE FROM Customer c WHERE c.id = " + customerId).executeUpdate();
        session.getTransaction().commit();
    }

    public static void getDeals(Session session, Long customerId) {
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);
        List<Deal> deals = customer.getDeals();
        System.out.println(customer.getName() + ":");

        for (Deal d : deals) {
            Product product = session.get(Product.class, d.getProduct().getId());
            System.out.println("Product: " + product.getName() + ",  Deal Price: " + d.getDealPrice() + ",  Current Price: " + product.getPrice());
        }

        session.getTransaction().commit();
    }
}
