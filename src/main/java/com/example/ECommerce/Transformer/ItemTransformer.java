package com.example.ECommerce.Transformer;

import com.example.ECommerce.DTO.RequestDtos.ItemRequestDto;
import com.example.ECommerce.Entity.Item;

public class ItemTransformer {

    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto)
    {
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }
}
