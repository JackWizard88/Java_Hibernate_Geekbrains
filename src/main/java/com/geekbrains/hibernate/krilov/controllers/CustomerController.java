package com.geekbrains.hibernate.krilov.controllers;

import com.geekbrains.hibernate.krilov.entities.Customer;
import org.hibernate.Session;

import java.util.List;

public class CustomerController {

    public static List<Customer> getAllCustomers(Session session) {
        session.beginTransaction();
        List<Customer> customers = session.createQuery("SELECT * FROM customers").getResultList();
        session.getTransaction().commit();
        return customers;
    }
}
