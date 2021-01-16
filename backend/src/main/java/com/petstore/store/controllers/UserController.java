package com.petstore.store.controllers;

import com.petstore.store.dao.AuthTokenDao;
import com.petstore.store.dao.CartDao;
import com.petstore.store.dao.UserDao;
import com.petstore.store.model.AuthRequest;
import com.petstore.store.model.AuthToken;
import com.petstore.store.model.Cart;
import com.petstore.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    private UserDao userDao;
    private CartDao cartDao;
    private AuthTokenDao authTokenDao;

    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserController(UserDao userDao, CartDao cartDao, AuthTokenDao authTokenDao, BCryptPasswordEncoder encoder){
        this.userDao = userDao;
        this.cartDao = cartDao;
        this.authTokenDao = authTokenDao;
        this.encoder = encoder;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> getAllUsers(){
         return userDao.findAll();
    }


}
