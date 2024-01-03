package com.example.ECommerce.DTO.ResponseDtos;

import com.example.ECommerce.Entity.Customer;
import com.example.ECommerce.Entity.Item;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {

    Integer totalCost;

    Integer numberOfItems;

    String customerName;

    List<ItemResponseDto> itemList;
}
