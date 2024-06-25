package az.pashabank.cardzone.service;

import az.pashabank.cardzone.dao.repository.CardRepository;
import az.pashabank.cardzone.mapper.CardMapper;
import az.pashabank.cardzone.model.dto.CardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;

    public List<CardDto> findAll() {
        return cardMapper.cardEntitiesToCardDtos(cardRepository.findAll());
    }

    // todo: add exception: card not found 404
    public CardDto findById(Long id) {
        return cardRepository.findById(id).map(cardMapper::cardToCardDTO).orElse(null);
    }

    public void create(CardDto cardDto) {
        var card = cardMapper.cardDTOToCard(cardDto);
        cardRepository.save(card);
    }

    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }
}
