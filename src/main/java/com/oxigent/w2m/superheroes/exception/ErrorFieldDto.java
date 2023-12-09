package com.oxigent.w2m.superheroes.exception;

import java.io.Serial;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ErrorFieldDto extends ErrorDto {

  @Serial private static final long serialVersionUID = -4227768165053202887L;

  private Map<String, String> errorFields;

  public ErrorFieldDto(String message, Map<String, String> errorFields) {
    super(message);
    this.errorFields = errorFields;
  }
}
