package com.petstore.store.dao;

import com.petstore.store.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, Long> {
    Category findAllById(long id);
}
