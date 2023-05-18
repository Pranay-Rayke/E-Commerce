package com.example.ECommerce.Service;

import com.example.ECommerce.DTO.RequestDtos.CardRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.CardResponseDto;
import com.example.ECommerce.Exception.CardAlreadyExistException;
import com.example.ECommerce.Exception.InvalidCustomerException;
import org.springframework.stereotype.Service;

public interface CardService {

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException, CardAlreadyExistException;
}
