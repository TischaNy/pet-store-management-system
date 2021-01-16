package com.petstore.store.dao;


import com.petstore.store.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetDao extends CrudRepository<Pet, Long> {
    Pet findById(long id);
    Pet findByName(String name);
}
