package ca.effenti.tutorial.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

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
                .setProperty("hibernate.hbm2ddl.auto", "create")
                .addAnnotatedClass(Song.class);
        sessionFactory = cfg.buildSessionFactory();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void whenExecuteQuery_givenAllSet_ThenReturnsRows() {
        Session session;

        // now lets pull events from the database and list them
        session = sessionFactory.openSession();
        List result = session.createNativeQuery("SELECT * FROM SONG").list();
        result.forEach(System.out::println);
        assertTrue(result.isEmpty());
        session.close();
    }

    @After
    public void tearDown(){
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}