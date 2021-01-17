package com.petstore.store.controllers;

import com.petstore.store.dao.CartItemDao;
import com.petstore.store.dao.CategoryDao;
import com.petstore.store.model.CartItem;
import com.petstore.store.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import restfullResponse.ApiController;

import javax.transaction.Transactional;

@RestController
@CrossOrigin
@RequestMapping("/cart-item")
public class CartItemController {
    private CartItemDao cartItemDao;
    private ApiController apiController;

    @Autowired
    public CartItemController(CartItemDao cartItemDao){
        this.cartItemDao = cartItemDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<CartItem> getAllCartItems(@RequestParam String cart){
        return cartItemDao.findAllByCart_Id(Long.parseLong(cart));
    }



    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ResponseBody
    public CartItem deleteCartItem(@RequestParam String id){
         return cartItemDao.deleteById(Long.parseLong(id));
    }

    @RequestMapping(value = "/cart", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional
    public ResponseEntity<ApiController> deleteAllCartItems(@RequestParam String id){

        try{
            cartItemDao.deleteAllByCart_Id(Long.parseLong(id));
            apiController = new ApiController("Deleted items", HttpStatus.OK);
        }catch(Exception e){
            apiController = new ApiController(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(apiController, apiController.getStatusCode());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus updateCartItem(@RequestBody CartItem cartItem){
        cartItemDao.save(cartItem);
        return HttpStatus.OK;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus insertCartItem(@RequestBody CartItem cartItem){
        cartItemDao.save(cartItem);
        return HttpStatus.OK;
    }

}
