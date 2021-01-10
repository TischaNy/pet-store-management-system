package com.petstore.store.dao;

import com.petstore.store.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderDao extends CrudRepository<Order, Long> {
    Order findAllById(long id);
}
