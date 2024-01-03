package com.example.ECommerce.Service.Impl;

import com.example.ECommerce.DTO.RequestDtos.CardRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.CardResponseDto;
import com.example.ECommerce.Entity.Card;
import com.example.ECommerce.Entity.Customer;
import com.example.ECommerce.Exception.CardAlreadyExistException;
import com.example.ECommerce.Exception.InvalidCustomerException;
import com.example.ECommerce.Repository.CardRepository;
import com.example.ECommerce.Repository.CustomerRepository;
import com.example.ECommerce.Service.CardService;
import com.example.ECommerce.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.ECommerce.Enum.CardType.VISA;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;
    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException, CardAlreadyExistException {

        Customer customer = customerRepository.findByMobileNo(cardRequestDto.getMobileNo());
        if(customer==null)
        {
            throw new InvalidCustomerException("Sorry!! The Customer Dosen't Exist");
        }

        Card alreadyExistCard = cardRepository.findByCardNo(cardRequestDto.getCardNo());

        if(alreadyExistCard != null)
        {
            throw new CardAlreadyExistException("Card Already Exist !!!");
        }
               Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
                card.setCustomer(customer);
                customer.getCardList().add(card);
                customerRepository.save(customer);
                return CardTransformer.CardToCardResponseDto(card);
            }

    @Override
    public List<CardResponseDto> getAllVisaCard() {

        List<Card> cardList = cardRepository.findByCardType(VISA);

        List<CardResponseDto> list = new ArrayList<>();

        for(Card card : cardList)
        {
            list.add(CardTransformer.CardToCardResponseDto(card));
        }
        return list;
    }
}
