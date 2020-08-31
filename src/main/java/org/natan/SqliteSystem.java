package org.natan;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SqliteSystem {
    protected static SessionFactory sessionFactory;
    private static Logger logger= LogManager.getLogger(SqliteSystem.class.getName());

    public static SessionFactory getSessionFactory(String databaseName) {
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
        config.setProperty("hibernate.connection.url", "jdbc:sqlite:" + databaseName + ".db");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
        config.setProperty("hibernate.hbm2ddl.auto", "update");
        config.setProperty("hibernate.show_sql", "true");

        config.addAnnotatedClass(Customer.class);

        SessionFactory sessionFactory = config.buildSessionFactory();

        return sessionFactory;
    }

    public static void main(String[] args) {
        try {
            sessionFactory = getSessionFactory("natan4");
            //create();
            read();
            delete();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



//    protected void setup() {
//        // code to load Hibernate Session factory
//    }

    protected void exit() {
        // code to close Hibernate Session factory
    }

    protected static void create() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("natan");
        customer.setLastName("pinsker");

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(customer);

        session.getTransaction().commit();
        session.close();
    }

    protected static void read() {
        Session session = sessionFactory.openSession();
        int index = 1;
        Customer customer = session.get(Customer.class, index);
        logger.info("customer - " + customer.getLastName() + customer.getFirstName());
        session.close();
    }

    protected void update() {
        // code to modify a book
    }

    protected static void delete() {
        sessionFactory.close();
    }
}
