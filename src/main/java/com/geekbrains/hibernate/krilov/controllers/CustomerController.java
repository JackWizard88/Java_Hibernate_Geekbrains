package com.geekbrains.hibernate.krilov.controllers;

import com.geekbrains.hibernate.krilov.entities.Customer;
import com.geekbrains.hibernate.krilov.entities.Product;
import org.hibernate.Session;
import java.util.List;

public class CustomerController {

    public static List<Customer> getAllCustomers(Session session) {
        session.beginTransaction();
        List<Customer> customers = session.createQuery("SELECT c FROM Customer c").getResultList();
        session.getTransaction().commit();
        return customers;
    }

    public static List<Product> getProductListByID(Session session, Long id) {
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        List<Product> cart = customer.getCart();
        System.out.println(cart);               // ПОЧЕМУ если эту строку закомментировать то ошибка ленивой инициализации, хотя я должен был достать список продуктов раньше чем закрыл сессию и вернуть его
        session.getTransaction().commit();
        return cart;
    }
}
