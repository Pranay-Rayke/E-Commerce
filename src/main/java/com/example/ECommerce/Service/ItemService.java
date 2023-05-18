package com.example.ECommerce.Service;

import com.example.ECommerce.DTO.RequestDtos.ItemRequestDto;
import com.example.ECommerce.Entity.Item;
import com.example.ECommerce.Exception.InvalidCustomerException;
import com.example.ECommerce.Exception.ProductNotAvailableException;
import com.example.ECommerce.Exception.ProductNotFoundException;
import com.example.ECommerce.Exception.RequiredQuantityNotAvailable;



public interface ItemService {

    public Item addItem(ItemRequestDto itemRequestDto) throws InvalidCustomerException, ProductNotFoundException, RequiredQuantityNotAvailable, ProductNotAvailableException;
}
