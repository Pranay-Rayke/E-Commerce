package com.example.ECommerce.Transformer;

import com.example.ECommerce.DTO.RequestDtos.CardRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.CardResponseDto;
import com.example.ECommerce.Entity.Card;

public class CardTransformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto)
    {
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .expiryDate(cardRequestDto.getExpiryDate())
                .build();
    }

    public static CardResponseDto CardToCardResponseDto(Card card)
    {
        return CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .customerName(card.getCustomer().getName())
                .build();
    }
}
