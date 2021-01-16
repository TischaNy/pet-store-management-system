package com.petstore.store.controllers;

import com.petstore.store.dao.CategoryDao;
import com.petstore.store.dao.PetDao;
import com.petstore.store.model.Category;
import com.petstore.store.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/pet")
public class PetController {
    private PetDao petDao;

    @Autowired
    public PetController(PetDao petDao){
        this.petDao = petDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Pet> getAllPets(){
        return petDao.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus insertPet(@RequestBody Pet pet){
        petDao.save(pet);
        return HttpStatus.OK;
    }
}
