package com.oxigent.w2m.superheroes.exception;

import java.io.Serial;
import lombok.Getter;

@Getter
public abstract class SuperheroesException extends Exception {

  @Serial private static final long serialVersionUID = -5278752297389156715L;
  private final int code;

  protected SuperheroesException(int code, String message) {
    super(message);
    this.code = code;
  }
}
