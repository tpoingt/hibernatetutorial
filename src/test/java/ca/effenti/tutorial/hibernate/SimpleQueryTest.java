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
    public void whenExecuteQuery_givenAllSet_ThenReturnsRows() {
        this.initSongs();
        Session session;

        // now lets pull events from the database and list them
        session = sessionFactory.openSession();
        session.beginTransaction();
        List<Song> result = session.createQuery("from Song").list();
        assertEquals(2, result.size());
        result.forEach(System.out::println);

        session.getTransaction().commit();
        session.close();
    }

    private void initSongs() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Song("Nothing else matters", LocalDate.parse("1992-04-20")));
        session.save(new Song("House of the rising sun", LocalDate.parse("1934-06-01")));
        session.getTransaction().commit();
        session.close();
    }

    @After
    public void tearDown(){
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}