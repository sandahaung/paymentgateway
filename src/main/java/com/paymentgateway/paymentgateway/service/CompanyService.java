package com.paymentgateway.paymentgateway.service;

import com.paymentgateway.paymentgateway.domain.Company;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing Companies
 *
 */
@Service("companyService")
@Transactional
public class CompanyService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Retrieves all companies
     *
     * @return a list of companies
     */
    public List<Company> getAll() {        

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM Company");

        // Retrieve all
        return query.list();
    }

    /**
     * Retrieves a single company
     */
    public Company get(Long id) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing company first
        Company company = (Company) session.get(Company.class, id);

        return company;
    }
    
    /**
     * Retrieves a company with a given username
     */
    public Company getByUsername(String username) {        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Company WHERE username = :username");
        query.setParameter("username", username);
        return (Company) query.uniqueResult();    
    }

    /**
     * Adds a new company
     */
    public void add(Company company) {
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Save
        session.save(company);
    }

    /**
     * Updates an existing company
     */
    public void update(Company company) {
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Update
        session.update(company);
    }
    
    /**
     * Deletes an existing company
     * @param id the id of the existing company
     */
    public void delete(Long id) {
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing company first
        Company company = (Company) session.get(Company.class, id);

        // Delete 
        session.delete(company);
    }

    /**
     * Edits an existing company
     */
    public void edit(Company company) {
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing company via id
        Company existingCompany = (Company) session.get(Company.class, company.getId());

        // Assign updated values to this company
        existingCompany.setCompanyId(company.getCompanyId());
        existingCompany.setCompanyName(company.getCompanyName());
        existingCompany.setCompanyEmail(company.getCompanyEmail());
        existingCompany.setUsername(company.getUsername());
        existingCompany.setPassword(company.getPassword());
        existingCompany.setEnabled(company.isEnabled());

        // Save updates
        session.save(existingCompany);
    }
}