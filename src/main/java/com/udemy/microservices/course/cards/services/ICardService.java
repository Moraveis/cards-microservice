package com.udemy.microservices.course.cards.services;

import com.udemy.microservices.course.cards.dtos.CardDto;

public interface ICardService {
    void createCard(String mobileNumber);

    CardDto fetchCard(String mobileNumber);

    boolean updateCard(CardDto cardsDto);

    boolean deleteCard(String mobileNumber);

}
