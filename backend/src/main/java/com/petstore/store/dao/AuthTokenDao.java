package com.petstore.store.dao;

import com.petstore.store.model.AuthToken;
import org.springframework.data.repository.CrudRepository;

public interface AuthTokenDao extends CrudRepository<AuthToken, Long> {
    AuthToken findById(long id);
    AuthToken findByToken(String token);
    AuthToken findAuthTokenByUserUserName(String username);
}
