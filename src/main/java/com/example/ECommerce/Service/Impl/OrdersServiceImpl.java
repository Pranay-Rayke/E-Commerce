package com.example.ECommerce.Service.Impl;

import com.example.ECommerce.Entity.*;
import com.example.ECommerce.Service.OrdersService;
import com.example.ECommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    ProductService productService;
    @Override
    public Orders placeOrder(Customer customer, Card card) throws Exception {

        Cart cart = customer.getCart();

        Orders orders = new Orders();

        orders.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNo = generateMaskCard(card.getCardNo());

        orders.setCardUsed(maskedCardNo);

        orders.setCustomer(customer);

        List<Item> orderdItem = new ArrayList<>();
        for(Item item : cart.getItemList())
        {
            try
            {
                productService.decreaseProductQuantity(item);
                orderdItem.add(item);
            }
            catch (Exception e)
            {
                throw new Exception("Product out of stock !!");
            }
        }
        orders.setItemList(orderdItem);
        for(Item item : orderdItem)
        {
            item.setOrders(orders);
        }
        orders.setValue(cart.getTotalCost());

        return orders;
    }

    public String generateMaskCard(String cardNo)
    {
        String maskedCardNo = "";
        for(int i=0;i< cardNo.length()-4;i++)
        {
            maskedCardNo += 'X';
        }
        maskedCardNo += cardNo.substring(cardNo.length()-4);

        return maskedCardNo;
    }
}
