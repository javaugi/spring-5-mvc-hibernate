package com.spring5.dao;

import com.spring5.model.Product;
import java.util.List;

import jakarta.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImp implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public void saveAll(List<Product> products) {
        for (Product product : products) {
            sessionFactory.getCurrentSession().save(product);
        }
    }

    @Override
    public List<Product> list() {
        @SuppressWarnings("unchecked")
        TypedQuery<Product> query = sessionFactory.getCurrentSession().createQuery("from Product");
        return query.getResultList();
    }

    @Override
    public List<Product> list(int offset, int limit) {
        @SuppressWarnings("unchecked")
        TypedQuery<Product> query = sessionFactory.getCurrentSession().createQuery("from Product");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
}
