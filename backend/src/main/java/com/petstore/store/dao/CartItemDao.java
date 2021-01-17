package com.petstore.store.dao;

import com.petstore.store.model.CartItem;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CartItemDao extends CrudRepository<CartItem, Long> {
    CartItem findById(long id);
    List<CartItem> findAllByCart_Id(long id);
    CartItem deleteById(long id);
}
