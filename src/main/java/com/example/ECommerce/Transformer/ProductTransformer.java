package com.example.ECommerce.Transformer;

import com.example.ECommerce.DTO.RequestDtos.ProductRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.ProductResponseDto;
import com.example.ECommerce.Entity.Product;
import com.example.ECommerce.Enum.ProductStatus;

public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto)
    {
        return Product.builder()
                .name(productRequestDto.getProductName())
                .productCategory(productRequestDto.getProductCategory())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product)
    {
        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .quantity(product.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }
}
