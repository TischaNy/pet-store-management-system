package com.petstore.store.controllers;

import com.petstore.store.dao.ProductDao;
import com.petstore.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    private ProductDao productDao;

    @Autowired
    public ProductController(ProductDao productDao){
        this.productDao = productDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Product> getAllProducts() {
        return productDao.findAll();
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus insertProduct(@RequestBody Product product){
        productDao.save(product);
        return HttpStatus.OK;
    }
}
