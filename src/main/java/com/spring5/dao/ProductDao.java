package com.spring5.dao;

import com.spring5.model.Product;
import java.util.List;

public interface ProductDao {

    void save(Product product);

    void saveAll(List<Product> products);

    List<Product> list();
}
