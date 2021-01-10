package com.petstore.store.dao;

import com.petstore.store.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressDao extends CrudRepository<Address, Long> {
    Address findAllById(long id);
}
