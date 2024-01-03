package com.example.ECommerce.Transformer;

import com.example.ECommerce.DTO.RequestDtos.SellerRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.DeleteSellerResponseDto;
import com.example.ECommerce.DTO.ResponseDtos.GetSellerResponseDto;
import com.example.ECommerce.DTO.ResponseDtos.SellerResponseDto;
import com.example.ECommerce.Entity.Seller;

public class SellerTransformer {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto)
    {
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .age(sellerRequestDto.getAge())
                .mobileNo(sellerRequestDto.getMobileNo())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller)
    {
        return SellerResponseDto.builder()
                .name(seller.getName())
                .message("Seller Added Successfully")
                .build();
    }

    public static GetSellerResponseDto SellerToGetSellerResponseDto(Seller seller)
    {
        return GetSellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .emailId(seller.getEmailId())
                .mobileNo(seller.getMobileNo())
                .build();
    }

    public static DeleteSellerResponseDto SellerToDeleteSellerResponseDto(Seller seller)
    {
        return DeleteSellerResponseDto.builder()
                .sellerName(seller.getName())
                .message("Seller Deleted Successfully")
                .build();
    }
}
