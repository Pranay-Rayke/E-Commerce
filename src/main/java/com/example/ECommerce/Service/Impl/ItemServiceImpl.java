package com.example.ECommerce.Service.Impl;

import com.example.ECommerce.DTO.RequestDtos.ItemRequestDto;
import com.example.ECommerce.Entity.Customer;
import com.example.ECommerce.Entity.Item;
import com.example.ECommerce.Entity.Product;
import com.example.ECommerce.Enum.ProductStatus;
import com.example.ECommerce.Exception.InvalidCustomerException;
import com.example.ECommerce.Exception.ProductNotAvailableException;
import com.example.ECommerce.Exception.ProductNotFoundException;
import com.example.ECommerce.Exception.RequiredQuantityNotAvailable;
import com.example.ECommerce.Repository.CustomerRepository;
import com.example.ECommerce.Repository.ItemRepository;
import com.example.ECommerce.Repository.ProductRepository;
import com.example.ECommerce.Service.ItemService;
import com.example.ECommerce.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws InvalidCustomerException, ProductNotFoundException, RequiredQuantityNotAvailable, ProductNotAvailableException {

        Customer customer;
        try
        {
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new InvalidCustomerException("Customer dosen't exist !!");
        }

        Product product;
        try
        {
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch (Exception e)
        {
            throw new ProductNotFoundException("Product dosen't exist !!");
        }

        if(itemRequestDto.getRequiredQuantity() > product.getQuantity())
        {
            throw new RequiredQuantityNotAvailable("Required Quantity Is Not Available");
        }
        if(product.getProductStatus() != ProductStatus.AVAILABLE)
        {
            throw new ProductNotAvailableException("Out Of Stock !!!");
        }

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
        System.out.println(customer.getCart().getItemList().size());
        item.setCart(customer.getCart());
        item.setProduct(product);

        product.getItemList().add(item);

        //Product saveProduct = productRepository.save(product);

        //return itemRepository.save(item);

        //int size = product.getItemList().size();

        //return saveProduct.getItemList().get(size-1);

        return itemRepository.save(item);
    }
}
