package com.company.todo.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryDao {

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
//            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory()
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static final SessionFactory sessionFactory;
    public static SessionFactory getSession(){
        return sessionFactory;
    }
}

