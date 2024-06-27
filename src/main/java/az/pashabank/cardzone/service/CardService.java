package az.pashabank.cardzone.service;

import az.pashabank.cardzone.dao.repository.CardRepository;
import az.pashabank.cardzone.mapper.CardMapper;
import az.pashabank.cardzone.model.dto.CardDto;
import az.pashabank.cardzone.model.exception.NoCardFoundByIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;

    public List<CardDto> findAll() {
        return cardMapper.cardEntitiesToCardDtos(cardRepository.findAll());
    }

    public CardDto findById(Long cardId) {
        return cardMapper.cardEntityToCardDto(cardRepository.findById(cardId).orElseThrow(() -> new NoCardFoundByIdException("Card by id:" + cardId + " does not exist.")));
    }

    public void create(CardDto cardDto) {
        var card = cardMapper.cardDtoToCardEntity(cardDto);
        cardRepository.save(card);
    }

    public void deleteById(Long cardId) {
        cardRepository
                .findById(cardId)
                .ifPresent(card -> cardRepository.deleteById(card.getId()));
    }
}
