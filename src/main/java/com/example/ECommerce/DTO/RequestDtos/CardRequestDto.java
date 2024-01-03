package com.example.ECommerce.DTO.RequestDtos;

import com.example.ECommerce.Enum.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {

    String mobileNo;

    String cardNo;

    int cvv;

    Date expiryDate;

    CardType cardType;
}
