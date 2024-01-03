package com.example.ECommerce.Service;

import com.example.ECommerce.DTO.RequestDtos.CardRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.CardResponseDto;
import com.example.ECommerce.Exception.CardAlreadyExistException;
import com.example.ECommerce.Exception.InvalidCustomerException;

import java.util.List;

public interface CardService {

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException, CardAlreadyExistException;

    public List<CardResponseDto> getAllVisaCard();
}
