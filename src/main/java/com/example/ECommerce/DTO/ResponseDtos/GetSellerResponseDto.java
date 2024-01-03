package com.example.ECommerce.DTO.ResponseDtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class GetSellerResponseDto {
    String name;

    String emailId;

    String mobileNo;

    Integer age;
}
