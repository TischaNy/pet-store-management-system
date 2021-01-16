package com.petstore.store.dao;


import com.petstore.store.model.Category;
import com.petstore.store.model.Pet;
import com.petstore.store.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDao extends CrudRepository<Product, Long> {
    Product findAllByCode(long code);
    List<Product> findAllByCategoryAndPet(Category category, Pet pet);
    List<Product> findAllByCategory(Category category);
    List<Product> findAllByPet(Pet pet);
}
