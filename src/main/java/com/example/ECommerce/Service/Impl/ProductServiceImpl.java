package com.example.ECommerce.Service.Impl;

import com.example.ECommerce.DTO.RequestDtos.ProductRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.ProductResponseDto;
import com.example.ECommerce.Entity.Item;
import com.example.ECommerce.Entity.Orders;
import com.example.ECommerce.Entity.Product;
import com.example.ECommerce.Entity.Seller;
import com.example.ECommerce.Enum.ProductCategory;
import com.example.ECommerce.Enum.ProductStatus;
import com.example.ECommerce.Exception.SellerNotFoundException;
import com.example.ECommerce.Repository.ProductRepository;
import com.example.ECommerce.Repository.SellerRepository;
import com.example.ECommerce.Service.ProductService;
import com.example.ECommerce.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {

        Seller seller;
        try
        {
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch(Exception e)
        {
           throw new SellerNotFoundException("Seller Does Not Exist");
        }

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);

        product.setSeller(seller);

        seller.getProductList().add(product);

        sellerRepository.save(seller);

        return ProductTransformer.ProductToProductResponseDto(product);

    }

    @Override
    public List<ProductResponseDto> getAllProductByCategory(ProductCategory category) {

        List<Product> productList = productRepository.findByProductCategory(category);

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product : productList)
        {
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getAllProductByPriceAndCategory(int price, String productCategory) {

        List<Product> productList = productRepository.getAllProductsByPriceAndCategory(price,productCategory);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product : productList)
        {
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtos;
    }

    @Override
    public void decreaseProductQuantity(Item item) throws Exception {

            Product product = item.getProduct();

            int quantity = item.getRequiredQuantity();
            int currentQuantity = product.getQuantity();
            if(quantity>currentQuantity)
            {
                throw new Exception("Out of stock");
            }
            product.setQuantity(currentQuantity-quantity);
            if(product.getQuantity() == 0)
            {
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
        }


    //get all the product by seller emailId

    //delete a product by seller id and product id

    //return top 5 cheapest product

    //return top 5 costly product

    //return all product that have quantity less than 10

    //return cheapest product in particular category

    //return costly product in particular category
}
