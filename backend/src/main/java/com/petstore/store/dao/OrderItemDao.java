package com.petstore.store.dao;

import com.petstore.store.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemDao extends CrudRepository<OrderItem, Long> {
    OrderItem findAllById(long id);
}
