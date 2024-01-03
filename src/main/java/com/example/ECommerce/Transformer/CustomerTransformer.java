package com.example.ECommerce.Transformer;

import com.example.ECommerce.DTO.RequestDtos.CustomerRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.CustomerResponseDto;
import com.example.ECommerce.Entity.Customer;

public class CustomerTransformer {

    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto)
    {
        return Customer.builder()
                .name(customerRequestDto.getName())
                .address(customerRequestDto.getAddress())
                .age(customerRequestDto.getAge())
                .emailId(customerRequestDto.getEmailId())
                .mobileNo(customerRequestDto.getMobileNo())
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer)
    {
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("Welcome "+customer.getName()+" to E-Commerce Platform")
                .build();
    }
}
