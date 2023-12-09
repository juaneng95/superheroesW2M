package com.oxigent.w2m.superheroes.exception;

import java.io.Serial;

public class NotFoundSuperheroesException extends SuperheroesException {
  @Serial private static final long serialVersionUID = -8964209529274067097L;

  public NotFoundSuperheroesException(String message) {

    super(404, message);
  }
}
