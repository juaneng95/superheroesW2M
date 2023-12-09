package com.oxigent.w2m.superheroes.mapper;

import com.oxigent.w2m.superheroes.entity.SuperheroEntity;
import com.oxigent.w2m.superheroes.model.SuperheroDto;
import com.oxigent.w2m.superheroes.model.SuperheroEntityRest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuperheroesMapper {

  SuperheroEntity entityRestToEntity(SuperheroEntityRest src);

  SuperheroDto entityToDto(SuperheroEntity src);
}
