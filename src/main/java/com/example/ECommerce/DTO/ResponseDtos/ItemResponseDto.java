package com.example.ECommerce.DTO.ResponseDtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {

    String productName;

    int quantity;

    int priceOfOneItem;

    int totalItemPrice;
}
