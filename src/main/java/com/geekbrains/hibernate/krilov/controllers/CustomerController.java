package com.geekbrains.hibernate.krilov.controllers;

import com.geekbrains.hibernate.krilov.entities.Customer;
import com.geekbrains.hibernate.krilov.entities.Deal;
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

    public static void removeProductFromCart(Session session, Long productId, Long customerId) {
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);

        //если удалять так, то запись удаляется, но оставшиеся сохраняются с нулевой ценой
//        customer.getCart().removeIf(p -> p.getId().equals(productId));

        // а этим методом не удаляется из таблицы сделок
        customer.getDeals().removeIf(p -> p.getProduct_id().equals(productId));

        session.getTransaction().commit();
    }

    public static void getDealPrice(Session session, Long customerId) {
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);
        List<Deal> deals = customer.getDeals();
        System.out.println(customer.getName() + ":");

        for (Deal d : deals) {
            Product product = session.get(Product.class, d.getProduct_id());
            System.out.println("Product: " + product.getName() + ",  Deal Price: " + d.getDealPrice() + ",  Current Price: " + product.getPrice());
        }

        session.getTransaction().commit();
    }
}
