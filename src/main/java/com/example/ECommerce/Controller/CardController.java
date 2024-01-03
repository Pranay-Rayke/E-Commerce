package com.example.ECommerce.Controller;

import com.example.ECommerce.DTO.RequestDtos.CardRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.CardResponseDto;
import com.example.ECommerce.Exception.CardAlreadyExistException;
import com.example.ECommerce.Exception.InvalidCustomerException;
import com.example.ECommerce.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @RequestMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) throws InvalidCustomerException, CardAlreadyExistException {
        try {
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
        }
        catch (InvalidCustomerException e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (CardAlreadyExistException e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity getAllVisaCard()
    {
            List<CardResponseDto> cardResponseDtoList = cardService.getAllVisaCard();
            return new ResponseEntity(cardResponseDtoList, HttpStatus.OK);
    }

    //get all visa card
    //get all master cards whose expiry is grater than 1 jan 2025
    //return the cardCategory which has maximum number of that card
}

