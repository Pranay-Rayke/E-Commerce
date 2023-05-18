package com.example.ECommerce.DTO.ResponseDtos;
import com.example.ECommerce.Entity.Item;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {

    String orderNo;

    int value;

    Date orderDate;

    String cardUsed;

    List<ItemResponseDto> itemList;

    String customerName;
}
