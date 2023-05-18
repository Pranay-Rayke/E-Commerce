package com.example.ECommerce.Controller;

import com.example.ECommerce.DTO.RequestDtos.ProductRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.ProductResponseDto;
import com.example.ECommerce.Enum.ProductCategory;
import com.example.ECommerce.Exception.SellerNotFoundException;
import com.example.ECommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws SellerNotFoundException {
        return productService.addProduct(productRequestDto);
    }

    @GetMapping("/get/{category}")
    public List<ProductResponseDto> getAllProductByCategory(@PathVariable("category") ProductCategory category)
    {
        return productService.getAllProductByCategory(category);
    }

    @GetMapping("/get/{price}/{category}")
    public List<ProductResponseDto> getAllProductByPriceAndCategory(@PathVariable("price") int price, @PathVariable("category") String productCategory)
    {
        return productService.getAllProductByPriceAndCategory(price,productCategory);
    }
}

//view all