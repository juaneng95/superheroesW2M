package com.oxigent.w2m.superheroes.service;

import com.oxigent.w2m.superheroes.exception.SuperheroesException;
import com.oxigent.w2m.superheroes.model.SuperheroDto;
import com.oxigent.w2m.superheroes.model.SuperheroEntityRest;
import java.util.List;

public interface SuperheroesService {
  List<SuperheroDto> getAllSuperheroes() throws SuperheroesException;

  SuperheroDto getSuperheroById(Long id) throws SuperheroesException;

  List<SuperheroDto> getAllSuperheroesByPattern(String pattern) throws SuperheroesException;

  void createSuperhero(SuperheroEntityRest inBody) throws SuperheroesException;

  void updateSuperhero(Long id, SuperheroEntityRest inBody) throws SuperheroesException;

  void deleteSuperhero(Long id) throws SuperheroesException;
}
