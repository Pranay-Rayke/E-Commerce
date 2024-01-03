package com.example.ECommerce.Repository;

import com.example.ECommerce.Entity.Product;
import com.example.ECommerce.Enum.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByProductCategory(ProductCategory category);

    @Query(value = "select * from product p where p.price > :price and p.product_category =:category", nativeQuery = true)
    List<Product> getAllProductsByPriceAndCategory(int price, String category);
}
