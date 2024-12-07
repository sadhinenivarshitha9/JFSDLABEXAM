package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        // Insert records manually
        Customer customer1 = new Customer();
        customer1.setName("Alice");
        customer1.setEmail("alice@example.com");
        customer1.setAge(28);
        customer1.setLocation("New York");

        Customer customer2 = new Customer();
        customer2.setName("Bob");
        customer2.setEmail("bob@example.com");
        customer2.setAge(35);
        customer2.setLocation("San Francisco");

        session.save(customer1);
        session.save(customer2);

        transaction.commit();

        // Apply Criteria Queries
        System.out.println("===== Applying Criteria Queries =====");
        
        // 1. Equal Restriction
        Criteria criteria1 = session.createCriteria(Customer.class);
        criteria1.add(Restrictions.eq("name", "Alice"));
        List<Customer> result1 = criteria1.list();
        result1.forEach(customer -> System.out.println("Equal: " + customer.getName()));

        // 2. Not Equal Restriction
        Criteria criteria2 = session.createCriteria(Customer.class);
        criteria2.add(Restrictions.ne("location", "New York"));
        List<Customer> result2 = criteria2.list();
        result2.forEach(customer -> System.out.println("Not Equal: " + customer.getName()));

        // 3. Greater Than Restriction
        Criteria criteria3 = session.createCriteria(Customer.class);
        criteria3.add(Restrictions.gt("age", 30));
        List<Customer> result3 = criteria3.list();
        result3.forEach(customer -> System.out.println("Greater Than Age 30: " + customer.getName()));

        // 4. Between Restriction
        Criteria criteria4 = session.createCriteria(Customer.class);
        criteria4.add(Restrictions.between("age", 25, 40));
        List<Customer> result4 = criteria4.list();
        result4.forEach(customer -> System.out.println("Between 25 and 40: " + customer.getName()));

        // 5. Like Restriction
        Criteria criteria5 = session.createCriteria(Customer.class);
        criteria5.add(Restrictions.like("email", "%example.com"));
        List<Customer> result5 = criteria5.list();
        result5.forEach(customer -> System.out.println("Email Like '%example.com': " + customer.getName()));

        session.close();
    }
}
