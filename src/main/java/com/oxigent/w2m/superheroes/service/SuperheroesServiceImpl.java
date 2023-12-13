package com.oxigent.w2m.superheroes.service;

import com.oxigent.w2m.superheroes.entity.SuperheroEntity;
import com.oxigent.w2m.superheroes.exception.NotFoundSuperheroesException;
import com.oxigent.w2m.superheroes.exception.SuperheroAlreadyExistsException;
import com.oxigent.w2m.superheroes.exception.SuperheroesException;
import com.oxigent.w2m.superheroes.mapper.SuperheroesMapper;
import com.oxigent.w2m.superheroes.model.SuperheroDto;
import com.oxigent.w2m.superheroes.model.SuperheroEntityRest;
import com.oxigent.w2m.superheroes.repository.SuperheroesRepository;
import com.oxigent.w2m.superheroes.util.Constants;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuperheroesServiceImpl implements SuperheroesService {

  private final SuperheroesRepository shRepository;
  private final SuperheroesMapper shMapper;

  @Cacheable("superheroes")
  @Override
  public List<SuperheroDto> getAllSuperheroes() {
    return shRepository.findAll().stream().map(shMapper::entityToDto).toList();
  }

  @Override
  public SuperheroDto getSuperheroById(Long id) throws NotFoundSuperheroesException {

    return shMapper.entityToDto(
        shRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundSuperheroesException(Constants.SUPERHERO_NOT_FOUND)));
  }

  @Override
  public List<SuperheroDto> getAllSuperheroesByPattern(String pattern) {

    // FindAll with toLowerCase patter filter.
    return shRepository.findAll().stream()
        .filter(
            superheroEntity ->
                superheroEntity.getName().toLowerCase().contains(pattern.toLowerCase()))
        .map(shMapper::entityToDto)
        .toList();
  }

  @CacheEvict(value = "superheroes", allEntries = true)
  @Override
  public void createSuperhero(SuperheroEntityRest inBody) throws SuperheroesException {

    // Validate - mapper
    validateSuperheroUniqueName(inBody.getName());
    SuperheroEntity superheroEntity = shMapper.entityRestToEntity(inBody);

    // Setting
    superheroEntity.setIsActive(true);
    superheroEntity.setCreatedAt(new Timestamp(new Date().getTime()));
    superheroEntity.setModifiedAt(new Timestamp(new Date().getTime()));

    shRepository.save(superheroEntity);
  }

  @CacheEvict(value = "superheroes", allEntries = true)
  @Override
  public void updateSuperhero(Long id, SuperheroEntityRest inBody) throws SuperheroesException {

    // Getting the previous values
    SuperheroEntity previousEntity =
        shRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundSuperheroesException(Constants.SUPERHERO_NOT_FOUND));

    // If superhero name is not the same...
    if (!previousEntity.getName().equalsIgnoreCase(inBody.getName())) {
      validateSuperheroUniqueName(inBody.getName());
    }

    // Mapping and setting.
    SuperheroEntity superheroEntity = shMapper.entityRestToEntity(inBody);

    superheroEntity.setId(id);
    superheroEntity.setIsActive(true);
    superheroEntity.setModifiedAt(new Timestamp(new Date().getTime()));

    shRepository.save(superheroEntity);
  }

  @CacheEvict(value = "superheroes", allEntries = true)
  @Override
  public void deleteSuperhero(Long id) throws NotFoundSuperheroesException {
    // Looking for hero...
    SuperheroEntity superheroEntity =
        shRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundSuperheroesException(Constants.SUPERHERO_NOT_FOUND));

    // Deleting hero.
    shRepository.deleteById(superheroEntity.getId());
  }

  // Checks if a superhero name is unique.
  private void validateSuperheroUniqueName(String name) throws SuperheroesException {

    if (Boolean.TRUE.equals(shRepository.existsByName(name))) {
      throw new SuperheroAlreadyExistsException(Constants.SUPERHERO_VALUE_NAME);
    }
  }
}
