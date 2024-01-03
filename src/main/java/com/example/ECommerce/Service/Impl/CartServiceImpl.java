package com.example.ECommerce.Service.Impl;

import com.example.ECommerce.DTO.RequestDtos.CheckOutCartRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.CartResponseDto;
import com.example.ECommerce.DTO.ResponseDtos.ItemResponseDto;
import com.example.ECommerce.DTO.ResponseDtos.OrderResponseDto;
import com.example.ECommerce.Entity.*;
import com.example.ECommerce.Exception.CartEmptyException;
import com.example.ECommerce.Exception.InvalidCardException;
import com.example.ECommerce.Exception.InvalidCustomerException;
import com.example.ECommerce.Repository.CardRepository;
import com.example.ECommerce.Repository.CartRepository;
import com.example.ECommerce.Repository.CustomerRepository;
import com.example.ECommerce.Repository.OrdersRepository;
import com.example.ECommerce.Service.CartService;
import com.example.ECommerce.Service.ProductService;
import com.example.ECommerce.Transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrdersServiceImpl ordersService;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ProductService productService;

    @Override
    public CartResponseDto addToCart(int customerId, Item item) {

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getTotalCost() + item.getRequiredQuantity() * item.getProduct().getPrice();

        cart.setTotalCost(newTotal);

        cart.getItemList().add(item);

        //int currentNoOfItem = cart.getNumberOfItems() + item.getRequiredQuantity();
        cart.setNumberOfItems(cart.getItemList().size());

        Cart saveCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartTransformer.CartToCartResponseDto(saveCart);

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();

        for(Item item1 : cart.getItemList())
        {

            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setPriceOfOneItem(item1.getProduct().getPrice());
            itemResponseDto.setTotalItemPrice(item1.getRequiredQuantity() * item1.getProduct().getPrice());
            itemResponseDto.setProductName(item1.getProduct().getName());
            itemResponseDto.setQuantity(item1.getRequiredQuantity());

            itemResponseDtoList.add(itemResponseDto);
        }

        cartResponseDto.setItemList(itemResponseDtoList);
        return cartResponseDto;
    }

    @Override
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws Exception {

        Customer customer;
        try
        {
            customer = customerRepository.findById(checkOutCartRequestDto.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new InvalidCustomerException("Customer does not found !! Please Register");
        }

        Card card = cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());

        if(card ==null || checkOutCartRequestDto.getCvv() != card.getCvv() || card.getCustomer() != customer)
        {
            throw new InvalidCardException("Card Invalid !!");
        }

        Cart cart = customer.getCart();
        if(cart.getNumberOfItems() == 0)
        {
            throw new CartEmptyException("Cart is empty !!");
        }
        try {
            Orders orders = ordersService.placeOrder(customer, card);
            customer.getOrdersList().add(orders);
            cart.setNumberOfItems(0);
            cart.setTotalCost(0);
            cart.setItemList(new ArrayList<>());
       //     customerRepository.save(customer);
            Orders savedOrder = ordersRepository.save(orders);

            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderNo(savedOrder.getOrderNo());
            orderResponseDto.setOrderDate(savedOrder.getOrderDate());
            orderResponseDto.setCardUsed(savedOrder.getCardUsed());
            orderResponseDto.setValue(savedOrder.getValue());
            orderResponseDto.setCustomerName(customer.getName());

            List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();

            for(Item item : savedOrder.getItemList())
            {

                ItemResponseDto itemResponseDto = new ItemResponseDto();
                itemResponseDto.setPriceOfOneItem(item.getProduct().getPrice());
                itemResponseDto.setTotalItemPrice(item.getRequiredQuantity() * item.getProduct().getPrice());
                itemResponseDto.setProductName(item.getProduct().getName());
                itemResponseDto.setQuantity(item.getRequiredQuantity());

                itemResponseDtoList.add(itemResponseDto);
            }
            orderResponseDto.setItemList(itemResponseDtoList);
            return orderResponseDto;
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

}
