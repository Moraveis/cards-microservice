package com.udemy.microservices.course.cards.mappers;

import com.udemy.microservices.course.cards.dtos.CardDto;
import com.udemy.microservices.course.cards.enitties.Card;

public class CardMapper {

    public static CardDto mapToCardDto(Card Card, CardDto CardDto) {
        CardDto.setCardNumber(Card.getCardNumber());
        CardDto.setCardType(Card.getCardType());
        CardDto.setMobileNumber(Card.getMobileNumber());
        CardDto.setTotalLimit(Card.getTotalLimit());
        CardDto.setAvailableAmount(Card.getAvailableAmount());
        CardDto.setAmountUsed(Card.getAmountUsed());
        return CardDto;
    }

    public static Card mapToCard(CardDto CardDto, Card Card) {
        Card.setCardNumber(CardDto.getCardNumber());
        Card.setCardType(CardDto.getCardType());
        Card.setMobileNumber(CardDto.getMobileNumber());
        Card.setTotalLimit(CardDto.getTotalLimit());
        Card.setAvailableAmount(CardDto.getAvailableAmount());
        Card.setAmountUsed(CardDto.getAmountUsed());
        return Card;
    }
}
