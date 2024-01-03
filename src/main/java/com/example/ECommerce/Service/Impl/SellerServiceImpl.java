package com.example.ECommerce.Service.Impl;

import com.example.ECommerce.DTO.RequestDtos.SellerRequestDto;
import com.example.ECommerce.DTO.ResponseDtos.DeleteSellerResponseDto;
import com.example.ECommerce.DTO.ResponseDtos.GetSellerResponseDto;
import com.example.ECommerce.DTO.ResponseDtos.SellerResponseDto;
import com.example.ECommerce.Entity.Seller;
import com.example.ECommerce.Exception.EmailIdAlreadyPresentException;
import com.example.ECommerce.Exception.SellerNotFoundException;
import com.example.ECommerce.Repository.SellerRepository;
import com.example.ECommerce.Service.SellerService;
import com.example.ECommerce.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;


    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailIdAlreadyPresentException {


        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId()) != null)
            throw new EmailIdAlreadyPresentException("Email Id Already Present");

        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);

        Seller savedSeller = sellerRepository.save(seller);

        return SellerTransformer.SellerToSellerResponseDto(savedSeller);

    }

    @Override
    public GetSellerResponseDto getSellerByEmail(String email) throws SellerNotFoundException {

        if(sellerRepository.findByEmailId(email) == null) {
            throw new SellerNotFoundException("Seller not found");
        }
        else {
            Seller seller = sellerRepository.findByEmailId(email);
            return SellerTransformer.SellerToGetSellerResponseDto(seller);
        }
    }

    @Override
    public GetSellerResponseDto getSellerById(Integer id) throws SellerNotFoundException {
        if(sellerRepository.findById(id).isPresent()) {
            Seller seller = sellerRepository.findById(id).get();
            return SellerTransformer.SellerToGetSellerResponseDto(seller);
        }
        else {
            throw new SellerNotFoundException("Seller not found");
        }
    }

    @Override
    public List<GetSellerResponseDto> getAllSeller() {

        List<Seller> sellers = sellerRepository.findAll();

        List<GetSellerResponseDto> list = new ArrayList<>();

        for(Seller seller : sellers)
        {
            list.add(SellerTransformer.SellerToGetSellerResponseDto(seller));
        }
            return list;
        }

    @Override
    public GetSellerResponseDto updateSellerInfo(String MobileNo, String EmailId) throws SellerNotFoundException {

        if(sellerRepository.findByEmailId(EmailId) == null) {
            throw new SellerNotFoundException("Seller not found");
        }
        else {
            Seller seller = sellerRepository.findByEmailId(EmailId);
            seller.setMobileNo(MobileNo);
            sellerRepository.save(seller);
            return SellerTransformer.SellerToGetSellerResponseDto(seller);
        }

    }

    @Override
    public DeleteSellerResponseDto deleteSellerByEmail(String email) throws SellerNotFoundException {
        if(sellerRepository.findByEmailId(email) == null) {
            throw new SellerNotFoundException("Seller not found");
        }
        else {
            Seller seller = sellerRepository.findByEmailId(email);

            sellerRepository.delete(seller);

            return SellerTransformer.SellerToDeleteSellerResponseDto(seller);
        }
    }

    @Override
    public DeleteSellerResponseDto deleteSellerById(Integer Id) throws SellerNotFoundException {

        if(sellerRepository.findById(Id).isPresent()) {
            Seller seller = sellerRepository.findById(Id).get();
            sellerRepository.delete(seller);
            return SellerTransformer.SellerToDeleteSellerResponseDto(seller);
        }
        else {
            throw new SellerNotFoundException("Seller not found");
        }
    }

}

