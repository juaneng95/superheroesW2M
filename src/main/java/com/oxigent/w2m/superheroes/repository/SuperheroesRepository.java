package com.oxigent.w2m.superheroes.repository;

import com.oxigent.w2m.superheroes.entity.SuperheroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperheroesRepository extends JpaRepository<SuperheroEntity, Long> {

  // Method to know if a superhero exists by name.
  Boolean existsByName(String name);
}
