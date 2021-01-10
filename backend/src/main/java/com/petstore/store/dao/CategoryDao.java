package com.petstore.store.dao;

import com.petstore.store.model.Category;

public interface CategoryDao  {
    Category findAllByName(String name);
}
