package ca.effenti.tutorial.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
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
        Configuration cfg = new Configuration()
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                .setProperty("hibernate.connection.url", "jdbc:h2:mem:db1;DB_CLOSE_DELAY=-1;MVCC=TRUE")
                .setProperty("hibernate.connection.username", "sa")
                .setProperty("hibernate.connection.password", "")
                .setProperty("hibernate.connection.pool_size", "1")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "create");
        sessionFactory = cfg.buildSessionFactory();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void whenTestConnection_givenAllSet_ThenReturnsOne() {
        Session session;

        // now lets pull events from the database and list them
        session = sessionFactory.openSession();
        List result = session.createNativeQuery("SELECT 1").list();
        result.forEach(System.out::println);

        session.close();
        assertEquals(1, result.get(0));
    }

    @After
    public void tearDown(){
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}