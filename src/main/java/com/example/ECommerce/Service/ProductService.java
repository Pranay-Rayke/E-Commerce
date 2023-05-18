package com.example.ECommerce.Service;

import com.example.ECommerce.DTO.RequestDtos.ProductRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.ProductResponseDto;
import com.example.ECommerce.Entity.Item;
import com.example.ECommerce.Entity.Orders;
import com.example.ECommerce.Entity.Product;
import com.example.ECommerce.Enum.ProductCategory;
import com.example.ECommerce.Exception.SellerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;

    public List<ProductResponseDto> getAllProductByCategory(ProductCategory category);

    public List<ProductResponseDto> getAllProductByPriceAndCategory(int price, String productCategory);

    public void decreaseProductQuantity(Item item) throws Exception;
}
