package com.oxigent.w2m.superheroes.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
  // Exceptions
  public static final String SUPERHERO_ALREADY_EXISTS =
      "SUPERHERO ALREADY EXISTS - SuperHero already exists by ";
  public static final String SUPERHERO_NOT_FOUND =
      "SUPERHERO NOT FOUND - Superhero not exists in H2 Database.";
  // Values
  public static final String SUPERHERO_VALUE_NAME = "Name";
}
