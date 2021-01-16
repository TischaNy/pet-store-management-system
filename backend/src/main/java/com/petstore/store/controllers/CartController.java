package com.petstore.store.controllers;

import com.petstore.store.dao.CartDao;
import com.petstore.store.model.Cart;
import com.petstore.store.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    private CartDao cartDao;

    @Autowired
    public CartController(CartDao cartDao){
        this.cartDao = cartDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Cart> getAllCarts(){
        return cartDao.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus insertCart(@RequestBody Cart cart){
        cartDao.save(cart);
        return HttpStatus.OK;
    }
}
