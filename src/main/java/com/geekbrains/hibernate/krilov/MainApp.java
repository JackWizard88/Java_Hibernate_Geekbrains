package com.geekbrains.hibernate.krilov;

import com.geekbrains.hibernate.krilov.controllers.CustomerController;
import com.geekbrains.hibernate.krilov.controllers.ProductController;
import com.geekbrains.hibernate.krilov.entities.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.config.ConfigurationException;


public class MainApp {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
//        PrepareDB.prepareDB();

        System.out.println("Customers list from DB");
        System.out.println(CustomerController.getAllCustomers(getSessionFactory().getCurrentSession()));
        System.out.println("Products list from DB");
        System.out.println(ProductController.getAllProducts(getSessionFactory().getCurrentSession()));

        System.out.println("Products list from DB which are in cart of Customer with id = 2");
        System.out.println(CustomerController.getProductListByID(getSessionFactory().getCurrentSession(), 2L));
        System.out.println("Customers list from DB who have item with id = 1");
        System.out.println(ProductController.getCustomersListByID(getSessionFactory().getCurrentSession(), 1L));

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure("configs/hibernate.cfg.xml").buildSessionFactory();
            } catch (ConfigurationException noConfEx) {
                sessionFactory = new Configuration().configure("configs/hibernate.mock.cfg.xml").buildSessionFactory();
            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }
}
