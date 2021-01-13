package com.petstore.store.controllers;

import com.petstore.store.dao.OrderItemDao;
import com.petstore.store.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderitem")
public class OrderItemController {
    private OrderItemDao orderItemDao;

    @Autowired
    public OrderItemController(OrderItemDao orderItemDao){
        this.orderItemDao = orderItemDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<OrderItem> getAllOrderItems(){
        return orderItemDao.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus insertAddress(@RequestBody OrderItem orderItem){
        orderItemDao.save(orderItem);
        return HttpStatus.OK;
    }
}
