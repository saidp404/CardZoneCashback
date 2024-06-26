package az.pashabank.cardzone.service;

import az.pashabank.cardzone.dao.repository.CardRepository;
import az.pashabank.cardzone.mapper.CardMapper;
import az.pashabank.cardzone.model.dto.CardDto;
import az.pashabank.cardzone.model.exception.NoCardFoundById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;

    public List<CardDto> findAll() {
        return cardMapper.cardEntitiesToCardDtos(cardRepository.findAll());
    }

    public CardDto findById(Long cardId) throws NoCardFoundById {
        return cardMapper.cardEntityToCardDto(cardRepository.findById(cardId).orElseThrow(() -> new NoCardFoundById("Card by id:" + cardId + " does not exist.")));
    }

    public void create(CardDto cardDto) {
        var card = cardMapper.cardDtoToCardEntity(cardDto);
        card.setCreatedAt(LocalDateTime.now());
        cardRepository.save(card);
    }

    public void deleteById(Long cardId) throws NoCardFoundById {
        if (cardRepository.existsById(cardId)){
            cardRepository.deleteById(cardId);
        }
        else{
            throw new NoCardFoundById("Card by id:" + cardId + " cannot be deleted, as it does not exist.");
        }
    }
}
