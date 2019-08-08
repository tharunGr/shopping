package com.shopping.databaseconnection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;

public class DatabaseConnection {

    private static SessionFactory factory;

    private DatabaseConnection() {
    }

    public static SessionFactory getInstance() {
        try {
            if (factory == null) {
                factory = new Configuration().configure("com/shopping/hibernate.cfg.xml").buildSessionFactory();
            }
            return factory; 
        } catch (HibernateException e) {
            System.out.println(e);
            System.out.println("Exception occured at Session Factory");
        }
        return factory;
    }	
}










