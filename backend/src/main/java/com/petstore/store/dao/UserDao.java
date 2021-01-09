package com.petstore.store.dao;

import com.petstore.store.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserDao extends CrudRepository<User, Long> {
    User findAllByUserName(String userName);
}
