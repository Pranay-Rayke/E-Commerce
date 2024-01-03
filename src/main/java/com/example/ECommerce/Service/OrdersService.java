package com.example.ECommerce.Service;

import com.example.ECommerce.DTO.RequestDtos.OrderRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.OrderResponseDto;
import com.example.ECommerce.Entity.Card;
import com.example.ECommerce.Entity.Customer;
import com.example.ECommerce.Entity.Orders;
import com.example.ECommerce.Exception.*;
import org.springframework.stereotype.Service;

public interface OrdersService {

    public Orders placeOrder(Customer customer, Card card) throws Exception;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;
}
