package com.petstore.store.controllers;

import com.petstore.store.dao.OrderItemDao;
import com.petstore.store.model.Order;
import com.petstore.store.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import restfullResponse.ApiController;

@RestController
@CrossOrigin
@RequestMapping("/order-item")
public class OrderItemController {
    private OrderItemDao orderItemDao;
    private ApiController apiController;
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
    public ResponseEntity<ApiController> insertOrder(@RequestBody OrderItem orderItem){
        try{
            OrderItem savedOrderItem =  orderItemDao.save(orderItem);
            apiController = new ApiController(savedOrderItem, "Order Iteù created", HttpStatus.OK);
        }catch(Exception e){
            apiController = new ApiController(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(apiController, apiController.getStatusCode());
    }
}
