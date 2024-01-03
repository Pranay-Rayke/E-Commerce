package com.example.ECommerce.Service;

import com.example.ECommerce.DTO.RequestDtos.SellerRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.DeleteSellerResponseDto;
import com.example.ECommerce.DTO.ResponseDtos.GetSellerResponseDto;
import com.example.ECommerce.DTO.ResponseDtos.SellerResponseDto;
import com.example.ECommerce.Entity.Seller;
import com.example.ECommerce.Exception.EmailIdAlreadyPresentException;
import com.example.ECommerce.Exception.SellerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SellerService {

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailIdAlreadyPresentException;

    public GetSellerResponseDto getSellerByEmail(String email) throws SellerNotFoundException;

    public GetSellerResponseDto getSellerById(Integer id) throws SellerNotFoundException;

    public List<GetSellerResponseDto> getAllSeller();

    public GetSellerResponseDto updateSellerInfo(String MobileNo, String EmailId) throws SellerNotFoundException;

    public DeleteSellerResponseDto deleteSellerByEmail(String email) throws SellerNotFoundException;

    public DeleteSellerResponseDto deleteSellerById(Integer Id) throws SellerNotFoundException;
}
