package ca.effenti.tutorial.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimpleQueryTest {
    private SessionFactory sessionFactory;

    @Before
    public void setUp() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void whenTestConnection_givenAllSet_ThenReturnsOne() {
        Session session = sessionFactory.openSession();
        List result = session.createNativeQuery("SELECT 1").list();
        result.forEach(System.out::println);
        assertEquals(1, result.get(0));
        session.close();
    }

    @After
    public void tearDown(){
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}