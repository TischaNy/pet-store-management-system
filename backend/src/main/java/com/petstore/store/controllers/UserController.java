package com.petstore.store.controllers;

import com.petstore.store.dao.UserDao;
import com.petstore.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController {
    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao){
        this.userDao = userDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> getAllUsers(){
         return userDao.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus insertUser(@RequestBody User user){
        userDao.save(user);
        return HttpStatus.OK;
    }

}
