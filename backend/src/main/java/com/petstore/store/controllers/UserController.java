package com.petstore.store.controllers;

import com.petstore.store.dao.CartDao;
import com.petstore.store.dao.UserDao;
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
@RequestMapping("/user")
public class UserController {
    private UserDao userDao;
    private CartDao cartDao;

    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserController(UserDao userDao, CartDao cartDao, BCryptPasswordEncoder encoder){
        this.userDao = userDao;
        this.cartDao = cartDao;
        this.encoder = encoder;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> getAllUsers(){
         return userDao.findAll();
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus insertUser(@RequestBody User user){
        Cart cart = new Cart();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCart(cart);
        cartDao.save(cart);
        userDao.save(user);
        return HttpStatus.OK;
    }

}
