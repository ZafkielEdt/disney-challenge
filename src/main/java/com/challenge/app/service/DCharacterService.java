package com.challenge.app.service;

import com.challenge.app.exception.ElementAlreadyExistsException;
import com.challenge.app.exception.NotFoundException;
import com.challenge.app.mapper.DCharacterMapper;
import com.challenge.app.mapper.attribute.DCharacterAttributes;
import com.challenge.app.model.entity.DCharacter;
import com.challenge.app.model.entity.FilmSeries;
import com.challenge.app.model.request.DCharacterFilterRequest;
import com.challenge.app.model.request.DCharacterRequest;
import com.challenge.app.model.response.DCharacterResponse;
import com.challenge.app.model.response.ListDCharacterResponse;
import com.challenge.app.repository.DCharacterRepository;
import com.challenge.app.repository.FilmSeriesRepository;
import com.challenge.app.specification.DCharacterSpecification;
import com.challenge.app.service.abstraction.CreateDCharacter;
import com.challenge.app.service.abstraction.DeleteDCharacter;
import com.challenge.app.service.abstraction.GetDCharacter;
import com.challenge.app.service.abstraction.UpdateDCharacter;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DCharacterService implements CreateDCharacter, GetDCharacter, UpdateDCharacter,
    DeleteDCharacter {

  private final DCharacterRepository characterRepository;
  private final DCharacterMapper characterMapper;
  private final FilmSeriesRepository filmSeriesRepository;
  private final DCharacterSpecification dCharacterSpecification;

  @Override
  public DCharacterResponse create(DCharacterRequest request)
      throws ElementAlreadyExistsException, NotFoundException {

    if (characterRepository.existsByName(request.name())) {
      throw new ElementAlreadyExistsException("Character already exists");
    }

    DCharacter dCharacter = characterMapper.map(request);

    return characterMapper.map(characterRepository.save(dCharacter),
        DCharacterAttributes.ID,
        DCharacterAttributes.NAME,
        DCharacterAttributes.AGE,
        DCharacterAttributes.WEIGHT,
        DCharacterAttributes.STORY,
        DCharacterAttributes.IMAGE,
        DCharacterAttributes.FILM_SERIES);
  }

  @Override
  public DCharacterResponse get(Long id) throws NotFoundException {

    DCharacter dCharacter = characterRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Character not found"));

    return characterMapper.map(dCharacter,
        DCharacterAttributes.ID,
        DCharacterAttributes.NAME,
        DCharacterAttributes.AGE,
        DCharacterAttributes.WEIGHT,
        DCharacterAttributes.STORY,
        DCharacterAttributes.IMAGE,
        DCharacterAttributes.FILM_SERIES);
  }

  @Override
  public ListDCharacterResponse getAll() throws NotFoundException {

    List<DCharacter> dCharacters = characterRepository.findAll();

    if (dCharacters.isEmpty()) {
      throw new NotFoundException("Any character found");
    }

    return characterMapper.map(dCharacters);
  }

  @Override
  public ListDCharacterResponse getBy(String name, Long age, Set<Long> filmSeriesId,
      Pageable pageable) throws NotFoundException {

    DCharacterFilterRequest dCharacterFilterRequest = new DCharacterFilterRequest(
        name,
        age,
        filmSeriesId
    );

    Page<DCharacter> dCharacters = characterRepository.findAll(
        dCharacterSpecification.getByFilters(dCharacterFilterRequest), pageable);

    if (dCharacters.isEmpty()) {
      throw new NotFoundException("Any character found");
    }

    ListDCharacterResponse response = characterMapper.map(dCharacters.getContent());
    buildPageResponse(response, dCharacters);

    return response;
  }

  private void buildPageResponse(ListDCharacterResponse response, Page<DCharacter> page) {
    response.setPage(page.getNumber());
    response.setTotalPages(page.getTotalPages());
    response.setSize(page.getSize());
  }

  @Override
  public DCharacterResponse update(Long id, DCharacterRequest request) throws NotFoundException {

    DCharacter dCharacter = characterRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Character not found"));

    updateValues(dCharacter, request);

    return characterMapper.map(characterRepository.save(dCharacter),
        DCharacterAttributes.ID,
        DCharacterAttributes.NAME,
        DCharacterAttributes.AGE,
        DCharacterAttributes.WEIGHT,
        DCharacterAttributes.STORY,
        DCharacterAttributes.IMAGE,
        DCharacterAttributes.FILM_SERIES);
  }

  @Override
  public void delete(Long id) throws NotFoundException {
    DCharacter dCharacter = characterRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Character not found"));
    characterRepository.delete(dCharacter);
  }

  private void updateValues(DCharacter dCharacter, DCharacterRequest request)
      throws NotFoundException {
    if (!request.name().isBlank()) {
      dCharacter.setName(request.name());
    }
    if (request.age() != null) {
      dCharacter.setAge(request.age());
    }
    if (request.weight() != null) {
      dCharacter.setWeight(request.weight());
    }
    if (!request.story().isBlank()) {
      dCharacter.setStory(request.story());
    }
    if (!request.image().isBlank()) {
      dCharacter.setImage(request.image());
    }
    if (request.filmSeriesId() != null) {
      
      Set<FilmSeries> currentValues = new HashSet<>();

      for (Long filmId : request.filmSeriesId()) {
        currentValues.add(filmSeriesRepository.findById(filmId)
            .orElseThrow(() -> new NotFoundException("Film or series not found")));
      }

      dCharacter.setFilmSeries(currentValues);
    }
  }
}
