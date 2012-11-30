package com.paymentgateway.paymentgateway.service;

import com.paymentgateway.paymentgateway.domain.Customer;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing Customers
 *
 */
@Service("customerService")
@Transactional
public class CustomerService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Retrieves all customers
     *
     * @return a list of customers
     */
    public List<Customer> getAll() {        

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM Customer");

        // Retrieve all
        return query.list();
    }

    /**
     * Retrieves a single customer
     */
    public Customer get(Long id) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing customer first
        Customer customer = (Customer) session.get(Customer.class, id);

        return customer;
    }

    /**
     * Adds a new customer
     */
    public void add(Customer customer) {
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Save
        session.save(customer);
    }

    /**
     * Deletes an existing customer
     * @param id the id of the existing customer
     */
    public void delete(Long id) {
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing customer first
        Customer customer = (Customer) session.get(Customer.class, id);

        // Delete 
        session.delete(customer);
    }

    /**
     * Edits an existing customer
     */
    public void edit(Customer customer) {
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing customer via id
        Customer existingCustomer = (Customer) session.get(Customer.class, customer.getId());

        // Assign updated values to this customer
        existingCustomer.setEmail(customer.getEmail());

        // Save updates
        session.save(existingCustomer);
    }
}