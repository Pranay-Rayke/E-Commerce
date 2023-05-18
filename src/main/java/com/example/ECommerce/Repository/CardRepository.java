package com.example.ECommerce.Repository;

import com.example.ECommerce.Entity.Card;
import com.example.ECommerce.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findByCardNo(String cardNo);
}
