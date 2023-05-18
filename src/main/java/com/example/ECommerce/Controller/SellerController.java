package com.example.ECommerce.Controller;

import com.example.ECommerce.DTO.RequestDtos.SellerRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.GetSellerResponseDto;
import com.example.ECommerce.DTO.ResponseDtos.SellerResponseDto;
import com.example.ECommerce.Entity.Seller;
import com.example.ECommerce.Exception.EmailIdAlreadyPresentException;
import com.example.ECommerce.Exception.SellerNotFoundException;
import com.example.ECommerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){

        try {
            SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }
        catch (EmailIdAlreadyPresentException e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity getSellerByEmail(@RequestParam String emailId) {
        try {
            GetSellerResponseDto getSellerResponseDto = sellerService.getSellerByEmail(emailId);
            return new ResponseEntity(getSellerResponseDto,HttpStatus.OK);
        }
        catch (SellerNotFoundException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById")
    public ResponseEntity getSellerById(@RequestParam Integer id) {
        try {
            GetSellerResponseDto getSellerResponseDto = sellerService.getSellerById(id);
            return new ResponseEntity(getSellerResponseDto,HttpStatus.OK);
        }
        catch (SellerNotFoundException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //get all seller
    //update seller info based on email id
    //delete a seller based on email
    //delete by id
    //get all sellers of the particular age

}
