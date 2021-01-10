package com.petstore.store.controllers;

import com.petstore.store.dao.AddressDao;
import com.petstore.store.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/address")
public class AddressController {
    private AddressDao addressDao;

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
    public HttpStatus insertAddress(@RequestBody Address address){
        addressDao.save(address);
        return HttpStatus.OK;
    }

}
