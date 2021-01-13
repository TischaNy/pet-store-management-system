package com.petstore.store.dao;


import com.petstore.store.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartDao extends CrudRepository<Cart, Long> {
    Cart findAllById(long id);

}
