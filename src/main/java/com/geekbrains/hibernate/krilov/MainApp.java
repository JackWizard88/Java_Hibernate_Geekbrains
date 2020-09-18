package com.geekbrains.hibernate.krilov;

import com.geekbrains.hibernate.krilov.controllers.CustomerController;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.config.ConfigurationException;


public class MainApp {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        PrepareDB.prepareDB();

        //System.out.println(CustomerController.getAllCustomers(getSessionFactory().getCurrentSession()));

    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            } catch (ConfigurationException noConfEx) {
                sessionFactory = new Configuration().configure("hibernate.mock.cfg.xml").buildSessionFactory();
            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }
}
