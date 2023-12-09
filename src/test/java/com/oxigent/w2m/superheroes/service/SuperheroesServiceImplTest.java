package com.oxigent.w2m.superheroes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.oxigent.w2m.superheroes.entity.SuperheroEntity;
import com.oxigent.w2m.superheroes.exception.NotFoundSuperheroesException;
import com.oxigent.w2m.superheroes.exception.SuperheroAlreadyExistsException;
import com.oxigent.w2m.superheroes.exception.SuperheroesException;
import com.oxigent.w2m.superheroes.mapper.SuperheroesMapper;
import com.oxigent.w2m.superheroes.model.SuperheroDto;
import com.oxigent.w2m.superheroes.model.SuperheroEntityRest;
import com.oxigent.w2m.superheroes.repository.SuperheroesRepository;
import com.oxigent.w2m.superheroes.util.Constants;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SuperheroesServiceImplTest {

  @InjectMocks private SuperheroesServiceImpl shService;
  @Mock private SuperheroesRepository shRepository;
  @Mock private SuperheroesMapper shMapper;

  final EasyRandom easyRandom =
      new EasyRandom(
          new EasyRandomParameters()
              .objectPoolSize(1)
              .randomizationDepth(1)
              .stringLengthRange(5, 25)
              .collectionSizeRange(1, 1)
              .scanClasspathForConcreteTypes(true)
              .overrideDefaultInitialization(false)
              .ignoreRandomizationErrors(false));

  // Random Objects
  final SuperheroDto superheroDto = easyRandom.nextObject(SuperheroDto.class);
  final SuperheroEntityRest shEntityRest = easyRandom.nextObject(SuperheroEntityRest.class);
  final SuperheroEntity superheroEntity = easyRandom.nextObject(SuperheroEntity.class);
  final List<SuperheroEntity> superheroEntityList =
      easyRandom.objects(SuperheroEntity.class, 5).collect(Collectors.toList());

  @Test
  void getAllSuperheroes_OK() {
    // WHEN
    when(shRepository.findAll()).thenReturn(superheroEntityList);
    when(shMapper.entityToDto(any(SuperheroEntity.class))).thenReturn(superheroDto);

    // THEN
    List<SuperheroDto> res = shService.getAllSuperheroes();

    assertNotNull(res);
    assertEquals(superheroDto, res.getFirst());
  }

  @Test
  void getSuperheroById_OK() throws NotFoundSuperheroesException {
    // WHEN
    when(shRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(superheroEntity));
    when(shMapper.entityToDto(any(SuperheroEntity.class))).thenReturn(superheroDto);

    // THEN
    SuperheroDto res = shService.getSuperheroById(any(Long.class));

    assertNotNull(res);
    assertEquals(superheroDto, res);
    assertEquals(superheroDto.getId(), res.getId());
    assertEquals(superheroDto.getName(), res.getName());
  }

  @Test
  void getSuperheroById_NotFoundSuperheroesException() {
    // WHEN
    when(shRepository.findById(any(Long.class))).thenReturn(Optional.empty());

    // THEN
    NotFoundSuperheroesException ex =
        assertThrows(
            NotFoundSuperheroesException.class,
            () -> shService.getSuperheroById(any(Long.class)),
            Constants.SUPERHERO_NOT_FOUND);

    assertEquals(Constants.SUPERHERO_NOT_FOUND, ex.getMessage());
    assertEquals(404, ex.getCode());
  }

  @Test
  void getAllSuperheroesByPattern_OK() {
    // GIVEN
    superheroEntityList.get(0).setName("Superman");
    superheroEntityList.get(1).setName("SuperMAN");
    superheroEntityList.get(2).setName("SuperMan");
    superheroEntityList.get(3).setName("Echenique");
    superheroEntityList.get(4).setName("Black mamba");

    List<SuperheroDto> superheroList;

    // WHEN
    when(shRepository.findAll()).thenReturn(superheroEntityList);
    superheroList =
        superheroEntityList.stream()
            .filter(x -> x.getName().toLowerCase().contains("man".toLowerCase()))
            .map(shMapper::entityToDto)
            .toList();

    // THEN
    List<SuperheroDto> res = shService.getAllSuperheroesByPattern("man");

    assertNotNull(res);
    assertEquals(superheroList.size(), res.size());
    assertEquals(superheroList, res);
  }

  @Test
  void getAllSuperheroesByPattern_NoMatches() {
    // GIVEN
    superheroEntityList.get(0).setName("Hulk");
    superheroEntityList.get(1).setName("Captain America");
    superheroEntityList.get(2).setName("Travis");
    superheroEntityList.get(3).setName("Echenique");
    superheroEntityList.get(4).setName("Black mamba");

    List<SuperheroDto> superheroList;

    // WHEN
    when(shRepository.findAll()).thenReturn(superheroEntityList);
    superheroList =
        superheroEntityList.stream()
            .filter(x -> x.getName().toLowerCase().contains("man".toLowerCase()))
            .map(shMapper::entityToDto)
            .toList();

    // THEN
    List<SuperheroDto> res = shService.getAllSuperheroesByPattern("man");

    assertNotNull(res);
    assertEquals(superheroList, res);
  }

  @Test
  void createSuperhero_OK() throws SuperheroesException {
    // WHEN
    when(shRepository.existsByName(any(String.class))).thenReturn(Boolean.FALSE);
    when(shMapper.entityRestToEntity(any(SuperheroEntityRest.class))).thenReturn(superheroEntity);
    when(shRepository.save(any(SuperheroEntity.class))).thenReturn(superheroEntity);

    // THEN
    shService.createSuperhero(shEntityRest);

    verify(shRepository, times(1)).existsByName(any(String.class));
    verify(shMapper, times(1)).entityRestToEntity(any(SuperheroEntityRest.class));
    verify(shRepository, times(1)).save(superheroEntity);
  }
  @Test
  void createSuperhero_NameAlreadyExists(){
    // WHEN
    when(shRepository.existsByName(any(String.class))).thenReturn(Boolean.TRUE);

    // THEN
    SuperheroAlreadyExistsException ex =
        assertThrows(
            SuperheroAlreadyExistsException.class,
            () -> shService.createSuperhero(shEntityRest),
            Constants.SUPERHERO_NOT_FOUND);

    assertEquals(Constants.SUPERHERO_ALREADY_EXISTS+"Name", ex.getMessage());
    assertEquals(400, ex.getCode());
  }

  @Test
  void updateSuperhero_OK() throws SuperheroesException {
    // WHEN
    when(shRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(superheroEntity));
    when(shRepository.existsByName(any(String.class))).thenReturn(Boolean.FALSE);
    when(shMapper.entityRestToEntity(any(SuperheroEntityRest.class))).thenReturn(superheroEntity);
    when(shRepository.save(any(SuperheroEntity.class))).thenReturn(superheroEntity);

    // THEN
    shService.updateSuperhero(superheroDto.getId(), shEntityRest);

    verify(shRepository, times(1)).findById(superheroDto.getId());
    verify(shRepository, times(1)).existsByName(any(String.class));
    verify(shMapper, times(1)).entityRestToEntity(any(SuperheroEntityRest.class));
    verify(shRepository, times(1)).save(any(SuperheroEntity.class));
  }

  @Test
  void deleteSuperhero_OK() throws NotFoundSuperheroesException {
    // WHEN
    when(shRepository.findById(any(Long.class)))
        .thenReturn(Optional.of(superheroEntityList.get(4)));
    shService.deleteSuperhero(any(Long.class));

    // THEN
    verify(shRepository).deleteById(superheroEntityList.get(4).getId());
  }

  @Test
  void deleteSuperhero_NotFoundSuperheroesException() {
    // WHEN
    when(shRepository.findById(any(Long.class))).thenReturn(Optional.empty());

    // THEN
    NotFoundSuperheroesException ex =
        assertThrows(
            NotFoundSuperheroesException.class,
            () -> shService.deleteSuperhero(any(Long.class)),
            Constants.SUPERHERO_NOT_FOUND);

    assertEquals(Constants.SUPERHERO_NOT_FOUND, ex.getMessage());
    assertEquals(404, ex.getCode());
  }

}
