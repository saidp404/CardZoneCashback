package az.pashabank.cardzone.service;

import az.pashabank.cardzone.dao.repository.CardRepository;
import az.pashabank.cardzone.mapper.CardMapper;
import az.pashabank.cardzone.model.dto.CardDto;
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

    // todo: add exception: card not found 404
    public CardDto findById(Long id) {
        return cardMapper.cardEntityToCardDto(cardRepository.findById(id).orElse(null));
    }

    public void create(CardDto cardDto) {
        var card = cardMapper.cardDtoToCardEntity(cardDto);
        card.setCreatedAt(LocalDateTime.now());
        cardRepository.save(card);
    }

    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }
}
