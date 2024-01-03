package com.example.ECommerce.Controller;

import com.example.ECommerce.DTO.RequestDtos.SellerRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.DeleteSellerResponseDto;
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

import java.util.List;

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
    @GetMapping("/getAllSeller")
    public ResponseEntity getAllSeller(){
        List<GetSellerResponseDto> getSellerResponseDtosList = sellerService.getAllSeller();
        return new ResponseEntity(getSellerResponseDtosList, HttpStatus.OK);
    }


    //update seller info based on email id
    @PutMapping("/updateSellerInfo")
    public ResponseEntity updateSellerInfo(@RequestParam String MobileNo, @RequestParam String EmailId)
    {
        try {
            GetSellerResponseDto getSellerResponseDto = sellerService.updateSellerInfo(MobileNo, EmailId);
            return new ResponseEntity(getSellerResponseDto, HttpStatus.OK);
        }
        catch (SellerNotFoundException e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //delete a seller based on email
    @DeleteMapping("/delete")
    public ResponseEntity deleteSellerByEmail(@RequestParam String emailId) {
        try {
            DeleteSellerResponseDto deleteSellerResponseDto = sellerService.deleteSellerByEmail(emailId);
            return new ResponseEntity(deleteSellerResponseDto,HttpStatus.OK);
        }
        catch (SellerNotFoundException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //delete by id
    @DeleteMapping("/deleteById")
    public ResponseEntity deleteSellerById(@RequestParam Integer Id) {
        try {
            DeleteSellerResponseDto deleteSellerResponseDto = sellerService.deleteSellerById(Id);
            return new ResponseEntity(deleteSellerResponseDto,HttpStatus.OK);
        }
        catch (SellerNotFoundException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
