package org.natan;

import javax.persistence.*;

public class TestSystem {
    private static EntityManagerFactory ENTIRY_MANGER_FACTORY =
            Persistence.createEntityManagerFactory("Jpa");
    public static void main(String[] args) {
        addCustomer(3, "natan", "pinsker");
        addCustomer(5, "naty", "kabiatek");
        getCustomer(1);
        ENTIRY_MANGER_FACTORY.close();
    }

    public static void addCustomer(int id, String firstName, String lastName) {
        EntityManager entityManager = ENTIRY_MANGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Customer customer = new Customer();
            customer.setId(id);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            entityManager.persist(customer);
            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public static void getCustomer(int id) {
        EntityManager entityManager = ENTIRY_MANGER_FACTORY.createEntityManager();
        String querry = "SELECT c FROM Customer c WHERE c.id = :custID";
        TypedQuery<Customer> typedQuery = entityManager.createQuery(querry, Customer.class);
        typedQuery.setParameter("custID", id);
        Customer customer = null;
        try {
            customer = typedQuery.getSingleResult();
            System.out.println(customer.getFirstName());
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
