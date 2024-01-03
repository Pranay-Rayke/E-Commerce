package com.example.ECommerce.Repository;

import com.example.ECommerce.Entity.Card;
import com.example.ECommerce.Enum.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findByCardNo(String cardNo);

    List<Card> findByCardType(CardType CardType);
}
