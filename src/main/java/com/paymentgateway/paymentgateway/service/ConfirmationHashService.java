package com.paymentgateway.paymentgateway.service;

import com.paymentgateway.paymentgateway.domain.ConfirmationHash;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("confirmationHashService")
@Transactional
public class ConfirmationHashService {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    public void add(ConfirmationHash confirmationHash) {                
        Session session = sessionFactory.getCurrentSession();        
        session.save(confirmationHash);
    }
    
    public ConfirmationHash getByHash(String hash) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM ConfirmationHash WHERE hash = :hashValue");
        query.setParameter("hashValue", hash);
        return (ConfirmationHash) query.uniqueResult();
    }
    
    public void delete(long id) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing confirmation first
        ConfirmationHash confirmationHash = (ConfirmationHash) session.get(ConfirmationHash.class, id);

        // Delete 
        session.delete(confirmationHash);
    }
}
