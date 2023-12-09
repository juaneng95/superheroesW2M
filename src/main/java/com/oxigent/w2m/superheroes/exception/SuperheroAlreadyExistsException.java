package com.oxigent.w2m.superheroes.exception;

import com.oxigent.w2m.superheroes.util.Constants;
import java.io.Serial;

public class SuperheroAlreadyExistsException extends SuperheroesException {

  @Serial private static final long serialVersionUID = 6666179081409734387L;

  public SuperheroAlreadyExistsException(String field) {
    super(400, Constants.SUPERHERO_ALREADY_EXISTS.concat(field));
  }
}
