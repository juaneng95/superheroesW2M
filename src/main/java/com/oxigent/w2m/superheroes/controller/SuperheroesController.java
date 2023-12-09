package com.oxigent.w2m.superheroes.controller;

import com.oxigent.w2m.superheroes.api.SuperheroesApi;
import com.oxigent.w2m.superheroes.exception.SuperheroesException;
import com.oxigent.w2m.superheroes.model.SuperheroDto;
import com.oxigent.w2m.superheroes.model.SuperheroEntityRest;
import com.oxigent.w2m.superheroes.service.SuperheroesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuperheroesController implements SuperheroesApi {

  private final SuperheroesService shService;

  @Override
  public ResponseEntity<List<SuperheroDto>> getAllSuperheroes() throws SuperheroesException {

    List<SuperheroDto> superheroDtoList = shService.getAllSuperheroes().stream().toList();
    return ResponseEntity.ok(superheroDtoList);
  }

  @Override
  public ResponseEntity<SuperheroDto> getSuperheroById(Long id) throws SuperheroesException {

    return ResponseEntity.ok(shService.getSuperheroById(id));
  }

  @Override
  public ResponseEntity<List<SuperheroDto>> getSuperheroesByPattern(String pattern)
      throws SuperheroesException {

    List<SuperheroDto> superheroDtoList =
        shService.getAllSuperheroesByPattern(pattern).stream().toList();

    return ResponseEntity.ok(superheroDtoList);
  }

  @Override
  public ResponseEntity<Void> createSuperhero(@RequestBody SuperheroEntityRest inBody)
      throws SuperheroesException {

    shService.createSuperhero(inBody);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> updateSuperheroById(Long id, SuperheroEntityRest inBody)
      throws SuperheroesException {

    shService.updateSuperhero(id, inBody);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> deleteSuperheroById(Long id) throws SuperheroesException {

    shService.deleteSuperhero(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
