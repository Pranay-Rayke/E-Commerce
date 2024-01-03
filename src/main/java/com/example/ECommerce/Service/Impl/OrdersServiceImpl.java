package com.example.ECommerce.Service.Impl;

import com.example.ECommerce.DTO.RequestDtos.OrderRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.ItemResponseDto;
import com.example.ECommerce.DTO.ResponseDtos.OrderResponseDto;
import com.example.ECommerce.Entity.*;
import com.example.ECommerce.Enum.ProductStatus;
import com.example.ECommerce.Exception.*;
import com.example.ECommerce.Repository.CardRepository;
import com.example.ECommerce.Repository.CustomerRepository;
import com.example.ECommerce.Repository.OrdersRepository;
import com.example.ECommerce.Repository.ProductRepository;
import com.example.ECommerce.Service.OrdersService;
import com.example.ECommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public Orders placeOrder(Customer customer, Card card) throws Exception {

        Cart cart = customer.getCart();

        Orders orders = new Orders();

        orders.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNo = generateMaskCard(card.getCardNo());

        orders.setCardUsed(maskedCardNo);

        orders.setCustomer(customer);

        List<Item> orderdItem = new ArrayList<>();
        for(Item item : cart.getItemList())
        {
            try
            {
                productService.decreaseProductQuantity(item);
                orderdItem.add(item);
            }
            catch (Exception e)
            {
                throw new Exception("Product out of stock !!");
            }
        }
        orders.setItemList(orderdItem);
        for(Item item : orderdItem)
        {
            item.setOrders(orders);
        }
        orders.setValue(cart.getTotalCost());

        return orders;
    }

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
        Customer customer;
        try
        {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new InvalidCustomerException("Customer dosen't exist !!");
        }

        Product product;
        try
        {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e)
        {
            throw new ProductNotFoundException("Product dosen't exist !!");
        }

        if(orderRequestDto.getRequiredQuantity() > product.getQuantity())
        {
            throw new RequiredQuantityNotAvailable("Required Quantity Is Not Available");
        }
        if(product.getProductStatus() != ProductStatus.AVAILABLE)
        {
            throw new ProductNotAvailableException("Out Of Stock !!!");
        }

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());

        if(card ==null || orderRequestDto.getCvv() != card.getCvv() || card.getCustomer() != customer)
        {
            throw new InvalidCardException("Card Invalid !!");
        }

        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();

        try
        {
            productService.decreaseProductQuantity(item);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }

        Orders orders = new Orders();
        orders.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = generateMaskCard(card.getCardNo());
        orders.setCardUsed(maskedCardNo);
        orders.setCustomer(customer);
        orders.setValue(item.getRequiredQuantity() * product.getPrice());
        orders.getItemList().add(item);

        customer.getOrdersList().add(orders);

        item.setOrders(orders);

        product.getItemList().add(item);

        Orders savedOrder = ordersRepository.save(orders);

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderNo(savedOrder.getOrderNo());
        orderResponseDto.setOrderDate(savedOrder.getOrderDate());
        orderResponseDto.setCardUsed(savedOrder.getCardUsed());
        orderResponseDto.setValue(savedOrder.getValue());
        orderResponseDto.setCustomerName(customer.getName());

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();

        for(Item item1 : savedOrder.getItemList())
        {

            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setPriceOfOneItem(item1.getProduct().getPrice());
            itemResponseDto.setTotalItemPrice(item1.getRequiredQuantity() * item.getProduct().getPrice());
            itemResponseDto.setProductName(item1.getProduct().getName());
            itemResponseDto.setQuantity(item1.getRequiredQuantity());

            itemResponseDtoList.add(itemResponseDto);
        }
        orderResponseDto.setItemList(itemResponseDtoList);
        return orderResponseDto;
    }

    public String generateMaskCard(String cardNo)
    {
        String maskedCardNo = "";
        for(int i=0;i< cardNo.length()-4;i++)
        {
            maskedCardNo += 'X';
        }
        maskedCardNo += cardNo.substring(cardNo.length()-4);

        return maskedCardNo;
    }
}
