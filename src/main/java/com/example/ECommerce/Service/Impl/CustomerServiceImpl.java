package com.example.ECommerce.Service.Impl;

import com.example.ECommerce.DTO.RequestDtos.CustomerRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.CustomerResponseDto;
import com.example.ECommerce.Entity.Cart;
import com.example.ECommerce.Entity.Customer;
import com.example.ECommerce.Exception.EmailIdAlreadyPresentException;
import com.example.ECommerce.Repository.CustomerRepository;
import com.example.ECommerce.Service.CustomerService;
import com.example.ECommerce.Transformer.CartTransformer;
import com.example.ECommerce.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws EmailIdAlreadyPresentException {

            if(customerRepository.findByEmailId(customerRequestDto.getEmailId())!=null)
            {
                throw new EmailIdAlreadyPresentException("Customer Already Exist");
            }
            else
            {
                Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);
                Cart cart = CartTransformer.buildCart(customer);
                customer.setCart(cart);
                Customer customerSaved = customerRepository.save(customer);
                return CustomerTransformer.customerToCustomerResponseDto(customerSaved);

            }
    }
}
