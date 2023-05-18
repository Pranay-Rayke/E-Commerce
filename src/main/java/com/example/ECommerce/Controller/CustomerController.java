package com.example.ECommerce.Controller;

import com.example.ECommerce.DTO.RequestDtos.CustomerRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.CustomerResponseDto;
import com.example.ECommerce.Exception.EmailIdAlreadyPresentException;
import com.example.ECommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws EmailIdAlreadyPresentException {

        try {
            CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);
            return new ResponseEntity(customerResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // view all customer
    //get a customer by email and mob
    //get all customer whoes age grater than 25
    //get all customer who use visa card
    //update a customer info by email
    //delete a customer by email/mob
}
