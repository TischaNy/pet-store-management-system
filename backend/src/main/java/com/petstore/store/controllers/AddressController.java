package com.petstore.store.controllers;

import com.petstore.store.dao.AddressDao;
import com.petstore.store.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import restfullResponse.ApiController;

@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressController {
    private AddressDao addressDao;
    private ApiController apiController;

    @Autowired
    public AddressController(AddressDao addressDao){
        this.addressDao = addressDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Address> getAllAddresses(){
        return addressDao.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ApiController> insertAddress(@RequestBody Address address){
        try{
            Address savedAddress =  addressDao.save(address);
            apiController = new ApiController(savedAddress, "Address created", HttpStatus.OK);
        }catch(Exception e){
            apiController = new ApiController(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(apiController, apiController.getStatusCode());
    }

}
