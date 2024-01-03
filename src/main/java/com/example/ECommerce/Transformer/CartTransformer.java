package com.example.ECommerce.Transformer;

import com.example.ECommerce.DTO.ResponseDtos.CartResponseDto;
import com.example.ECommerce.Entity.Cart;
import com.example.ECommerce.Entity.Customer;

public class CartTransformer {

    public static CartResponseDto CartToCartResponseDto(Cart cart)
    {
        return CartResponseDto.builder()
                .totalCost(cart.getTotalCost())
                .numberOfItems(cart.getNumberOfItems())
                .customerName(cart.getCustomer().getName())
                .build();
    }

    public static Cart buildCart(Customer customer)
    {
        return Cart.builder()
                .numberOfItems(0)
                .totalCost(0)
                .customer(customer)
                .build();
    }
}
