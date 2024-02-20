package com.udemy.microservices.course.cards.services.impl;

import com.udemy.microservices.course.cards.constants.CardConstants;
import com.udemy.microservices.course.cards.dtos.CardDto;
import com.udemy.microservices.course.cards.enitties.Card;
import com.udemy.microservices.course.cards.exceptions.CardAlreadyExistsException;
import com.udemy.microservices.course.cards.exceptions.ResourceNotFoundException;
import com.udemy.microservices.course.cards.mappers.CardMapper;
import com.udemy.microservices.course.cards.repositories.CardRepository;
import com.udemy.microservices.course.cards.services.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService {

    private final CardRepository cardRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCards = cardRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber " + mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
        Card cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        return CardMapper.mapToCardDto(cards, new CardDto());
    }

    @Override
    public boolean updateCard(CardDto cardsDto) {
        Card cards = cardRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(() -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardMapper.mapToCard(cardsDto, cards);
        cardRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        cardRepository.deleteById(cards.getCardId());
        return true;
    }
}
