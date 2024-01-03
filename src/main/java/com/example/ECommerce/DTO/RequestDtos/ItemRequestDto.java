package com.example.ECommerce.DTO.RequestDtos;

import com.example.ECommerce.DTO.ResponseDtos.ItemResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemRequestDto {

    int customerId;

    int productId;

    int requiredQuantity;

}
