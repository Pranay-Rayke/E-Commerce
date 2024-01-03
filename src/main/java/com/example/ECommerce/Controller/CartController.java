package com.example.ECommerce.Controller;

import com.example.ECommerce.DTO.RequestDtos.CheckOutCartRequestDto;
import com.example.ECommerce.DTO.RequestDtos.ItemRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.CartResponseDto;
import com.example.ECommerce.DTO.ResponseDtos.OrderResponseDto;
import com.example.ECommerce.Entity.Item;
import com.example.ECommerce.Exception.InvalidCustomerException;
import com.example.ECommerce.Exception.ProductNotAvailableException;
import com.example.ECommerce.Exception.ProductNotFoundException;
import com.example.ECommerce.Exception.RequiredQuantityNotAvailable;
import com.example.ECommerce.Service.CartService;
import com.example.ECommerce.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) throws InvalidCustomerException, ProductNotFoundException, ProductNotAvailableException, RequiredQuantityNotAvailable {
        try
        {
            Item savedItem = itemService.addItem(itemRequestDto);
             CartResponseDto cartResponseDto = cartService.addToCart(itemRequestDto.getCustomerId(),savedItem);
             return new ResponseEntity(cartResponseDto,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")
    public OrderResponseDto checkOutCart(@RequestBody CheckOutCartRequestDto checkOutCartRequestDto) throws Exception {
       return cartService.checkOutCart(checkOutCartRequestDto);
    }
}

//remove from cart
//view all itmes in cart
//email sending
