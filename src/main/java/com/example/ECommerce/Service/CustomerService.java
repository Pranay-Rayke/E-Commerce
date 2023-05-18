package com.example.ECommerce.Service;

import com.example.ECommerce.DTO.RequestDtos.CustomerRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.CustomerResponseDto;
import com.example.ECommerce.Exception.EmailIdAlreadyPresentException;
import org.springframework.stereotype.Service;

public interface CustomerService {

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws EmailIdAlreadyPresentException;
}
