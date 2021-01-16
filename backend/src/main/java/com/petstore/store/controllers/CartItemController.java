package com.petstore.store.controllers;

import com.petstore.store.dao.CartItemDao;
import com.petstore.store.dao.CategoryDao;
import com.petstore.store.model.CartItem;
import com.petstore.store.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cart-item")
public class CartItemController {
    private CartItemDao cartItemDao;

    @Autowired
    public CartItemController(CartItemDao cartItemDao){
        this.cartItemDao = cartItemDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<CartItem> getAllCartItems(@RequestParam String cart){
        return cartItemDao.findAllByCart_Id(Long.parseLong(cart));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus insertCartItem(@RequestBody CartItem cartItem){
        cartItemDao.save(cartItem);
        return HttpStatus.OK;
    }

}
