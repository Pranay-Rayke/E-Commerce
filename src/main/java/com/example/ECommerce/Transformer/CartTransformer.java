package com.example.ECommerce.Transformer;

import com.example.ECommerce.DTO.ResponseDtos.CartResponseDto;
import com.example.ECommerce.Entity.Cart;

public class CartTransformer {

    public static CartResponseDto CartToCartResponseDto(Cart cart)
    {
        return CartResponseDto.builder()
                .totalCost(cart.getTotalCost())
                .numberOfItems(cart.getNumberOfItems())
                .customerName(cart.getCustomer().getName())
                .build();
    }
}
