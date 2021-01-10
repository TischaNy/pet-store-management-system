package com.petstore.store.dao;


import com.petstore.store.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product, Long> {
    Product findAllByCode(long code);

}
