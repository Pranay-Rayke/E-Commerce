package com.example.ECommerce.DTO.RequestDtos;


import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerRequestDto {

    String name;

    @Column(unique = true)
    String emailId;

    String mobileNo;

    Integer age;

    String address;
}

