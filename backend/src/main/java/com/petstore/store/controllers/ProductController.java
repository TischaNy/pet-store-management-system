package com.petstore.store.controllers;


import com.petstore.store.dao.CategoryDao;
import com.petstore.store.dao.PetDao;
import com.petstore.store.dao.ProductDao;
import com.petstore.store.model.Category;
import com.petstore.store.model.Pet;
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
    private CategoryDao categoryDao;
    private PetDao petDao;

    @Autowired
    public ProductController(ProductDao productDao, CategoryDao categoryDao, PetDao petDao){
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.petDao = petDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Product> getAllProducts(@RequestParam(required = false) String category, @RequestParam(required = false) String pet) {
        Category c = categoryDao.findByName(category);
        Pet p = petDao.findByName(pet);

        if(c != null && p != null){
            return productDao.findAllByCategoryAndPet(c, p);
        }else if(c != null){
            return productDao.findAllByCategory(c);
        }else if(p != null){
            return productDao.findAllByPet(p);
        }
        return productDao.findAll();
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus insertProduct(@RequestBody Product product){
        productDao.save(product);
        return HttpStatus.OK;
    }
}
