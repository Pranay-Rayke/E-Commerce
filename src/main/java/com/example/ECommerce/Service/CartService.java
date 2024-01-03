package com.example.ECommerce.Service;

import com.example.ECommerce.DTO.RequestDtos.CheckOutCartRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.CartResponseDto;
import com.example.ECommerce.DTO.ResponseDtos.OrderResponseDto;
import com.example.ECommerce.Entity.Item;
import com.example.ECommerce.Exception.CartEmptyException;
import com.example.ECommerce.Exception.InvalidCardException;
import com.example.ECommerce.Exception.InvalidCustomerException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CartService {

    public CartResponseDto addToCart(int customerId, Item item);

    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws Exception;

}
