package com.example.ECommerce.Service;

import com.example.ECommerce.Entity.Card;
import com.example.ECommerce.Entity.Customer;
import com.example.ECommerce.Entity.Orders;
import org.springframework.stereotype.Service;

public interface OrdersService {

    public Orders placeOrder(Customer customer, Card card) throws Exception;
}
