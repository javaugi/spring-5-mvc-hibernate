/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.service;

import com.spring5.dao.ProductRepository;
import com.spring5.model.Product;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    @Qualifier("productRepository")
    private ProductRepository productRepository;

    @Transactional
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional
    @Override
    public void saveAll(Iterable<Product> products) {
        productRepository.saveAll(products);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

}
