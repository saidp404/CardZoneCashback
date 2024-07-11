package az.pashabank.cardzone.service;

import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.dao.repository.CardRepository;
import az.pashabank.cardzone.mapper.CardMapper;
import az.pashabank.cardzone.model.dto.CardFilterDto;
import az.pashabank.cardzone.model.dto.CreationCardDto;
import az.pashabank.cardzone.model.dto.ResponseCardDto;
import az.pashabank.cardzone.model.exception.NoCardFoundByIdException;
import az.pashabank.cardzone.model.exception.NotUniquePanException;
import az.pashabank.cardzone.specification.CardSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;

    public Page<ResponseCardDto> listCards(Pageable pageable, CardFilterDto cardFilterDto) {
        Specification<CardEntity> specification = CardSpecifications.getSpecification(cardFilterDto);
        return cardMapper.toPageDto(cardRepository.findAll(specification, pageable));
    }

    public ResponseCardDto findById(Long cardId) {
        return cardMapper.cardEntityToResponseCardDto(cardRepository.findById(cardId).orElseThrow(() -> new NoCardFoundByIdException("Card by id:" + cardId + " does not exist.")));
    }

    public void create(CreationCardDto creationCardDto) {
        validateCard(creationCardDto);

        var card = cardMapper.creationCardDtoToCardEntity(creationCardDto);
        cardRepository.save(card);
    }

    public void deleteById(Long cardId) {
        cardRepository
                .findById(cardId)
                .ifPresent(card -> cardRepository.deleteById(card.getId()));
    }

    private void validateCard(CreationCardDto creationCardDto) {
        if (cardRepository.existsByPan(creationCardDto.pan())) {
            throw new NotUniquePanException("Such card already exists.");
        }
    }
}
