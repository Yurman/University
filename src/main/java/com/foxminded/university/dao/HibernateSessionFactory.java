package com.foxminded.university.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

@EnableTransactionManagement
@ComponentScan({ "com.foxminded.university" })
public class HibernateSessionFactory {

    private static SessionFactory sessionFactory;

    @Bean
    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Group.class);
            configuration.addAnnotatedClass(Department.class);
            configuration.addAnnotatedClass(Faculty.class);
            ServiceRegistry builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(builder);
        }
        return sessionFactory;
    }

}
