package com.oxigent.w2m.superheroes.exception;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto implements Serializable {

  @Serial private static final long serialVersionUID = -6353583885863143928L;

  private String message;
}
