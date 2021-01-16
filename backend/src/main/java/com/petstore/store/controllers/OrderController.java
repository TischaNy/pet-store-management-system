package com.petstore.store.controllers;

import com.petstore.store.dao.OrderDao;
import com.petstore.store.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    private OrderDao orderDao;

    @Autowired
    public OrderController(OrderDao orderDao){
        this.orderDao = orderDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Order> getAllOrders(){
        return orderDao.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus insertOrder(@RequestBody Order order){
        orderDao.save(order);
        return HttpStatus.OK;
    }

}
