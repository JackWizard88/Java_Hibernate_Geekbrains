package com.geekbrains.hibernate.krilov.controllers;

import com.geekbrains.hibernate.krilov.entities.Customer;
import com.geekbrains.hibernate.krilov.entities.Deal;
import com.geekbrains.hibernate.krilov.entities.Product;
import org.hibernate.Session;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Product> cart = customer.getCart();


        //если удалять так, то запись удаляется, но оставшиеся сохраняются с нулевой ценой
//        for (int i = 0; i < cart.size(); i++) {
//            if (cart.get(i).getId().equals(productId)) {
//                cart.remove(i);
//            }
//        }

        // а этим методом не удаляется из таблицы сделок
        customer.getDealPrices().removeIf(p -> p.getProduct_id().equals(productId));

        session.getTransaction().commit();
    }

    public static void getDealPrice(Session session, Long customerId) {
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);
        List<Deal> dealPrices = customer.getDealPrices();
        System.out.println(customer.getName() + ":");

        for (Deal d : dealPrices) {
            Product product = session.get(Product.class, d.getProduct_id());
            System.out.println("Product: " + product.getName() + ",  Deal Price: " + d.getDealPrice() + ",  Current Price: " + product.getPrice());
        }

        session.getTransaction().commit();
    }
}
