package com.geekbrains.hibernate.krilov;

import com.geekbrains.hibernate.krilov.controllers.CustomerController;
import com.geekbrains.hibernate.krilov.controllers.ProductController;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.config.ConfigurationException;


public class MainApp {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
//        PrepareDB.prepareDB();  //ошибка синтаксиса sql. Решить пока не получилось

        System.out.println("Customers list from DB");
        System.out.println(CustomerController.getAllCustomers(getSessionFactory().getCurrentSession()));
        System.out.println("Products list from DB");
        System.out.println(ProductController.getAllProducts(getSessionFactory().getCurrentSession()));

        System.out.println("===============================================================================");

        System.out.println("Products list from DB which are in cart of Customer with id = 2");

        System.out.println(CustomerController.getProductListByID(getSessionFactory().getCurrentSession(), 2L));
        System.out.println("Customers list from DB who have item with id = 1");
        System.out.println(ProductController.getCustomersListByID(getSessionFactory().getCurrentSession(), 1L));

        System.out.println("===============================================================================");

        System.out.println("Delete Customer with id = 2");
        CustomerController.removeCustomerById(getSessionFactory().getCurrentSession(), 2L);
        System.out.println("Customers list from DB after delete");
        System.out.println(CustomerController.getAllCustomers(getSessionFactory().getCurrentSession()));

        System.out.println("===============================================================================");

        System.out.println("Delete Product with id = 5");
        ProductController.removeProductById(getSessionFactory().getCurrentSession(), 5L);
        System.out.println("Product list from DB after delete");
        System.out.println(ProductController.getAllProducts(getSessionFactory().getCurrentSession()));

        //удаление товара из корзины покупателя. не работает
//        System.out.println("===============================================================================");
//        System.out.println("======= Before delete =======");
//        CustomerController.getDeals(getSessionFactory().getCurrentSession(), 1L);
//        CustomerController.removeProductFromCart(getSessionFactory().getCurrentSession(), 2L, 1L);
//        System.out.println("======= After delete =======");
//        CustomerController.getDeals(getSessionFactory().getCurrentSession(), 1L);

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
