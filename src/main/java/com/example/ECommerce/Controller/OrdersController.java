package com.example.ECommerce.Controller;

import com.example.ECommerce.DTO.RequestDtos.OrderRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.OrderResponseDto;
import com.example.ECommerce.Service.Impl.OrdersServiceImpl;
import com.example.ECommerce.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    OrdersService ordersService;
    @PostMapping("/place")
    public OrderResponseDto placeDirectOrder(@RequestBody OrderRequestDto orderRequestDto) throws Exception {
       return ordersService.placeOrder(orderRequestDto);
    }

}

//get all the order for customer
//get recent 5 orders
//delete an order from the order list
//select the order and also tell the customer name with highest total value
