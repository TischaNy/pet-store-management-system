package com.petstore.store.controllers;

import com.petstore.store.dao.CategoryDao;
import com.petstore.store.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/category")
public class CategoryController {
    private CategoryDao categoryDao;

    @Autowired
    public CategoryController(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Category> getAllCategories(){
        return categoryDao.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus insertCategory(@RequestBody Category category){
        categoryDao.save(category);
        return HttpStatus.OK;
    }

}
