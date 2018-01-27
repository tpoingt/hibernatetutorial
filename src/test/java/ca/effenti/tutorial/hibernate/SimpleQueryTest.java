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

import javax.persistence.EntityManager;
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
                .setProperty("hibernate.hbm2ddl.auto", "create")
                .addAnnotatedClass(Song.class);
        sessionFactory = cfg.buildSessionFactory();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void whenExecuteQuery_givenAllSet_ThenReturnsRows() {
        this.initSongs();
        EntityManager entityManager =  sessionFactory.openSession();
        entityManager.getTransaction().begin();
        System.out.println(entityManager.find(Song.class, 1L));
        System.out.println(entityManager.find(Song.class, 2L));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private void initSongs() {
        EntityManager entityManager = sessionFactory.openSession();
        entityManager.getTransaction().begin();
        entityManager.persist(new Song("Nothing else matters", LocalDate.parse("1992-04-20")));
        entityManager.persist(new Song("House of the rising sun", LocalDate.parse("1934-06-01")));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @After
    public void tearDown(){
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}