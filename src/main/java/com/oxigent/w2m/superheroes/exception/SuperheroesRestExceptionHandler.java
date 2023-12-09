package com.oxigent.w2m.superheroes.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class SuperheroesRestExceptionHandler {

  private SuperheroesRestExceptionHandler() {}

  @ExceptionHandler({Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public static ResponseEntity<ErrorDto> unhandledExceptions(HttpServletRequest req, Exception ex) {

    return ResponseEntity.internalServerError().body(new ErrorDto(ex.getMessage()));
  }

  @ExceptionHandler({SuperheroesException.class})
  @ResponseBody
  public static ResponseEntity<ErrorDto> handleSuperheroesCustomExceptions(
      HttpServletRequest req, HttpServletResponse res, SuperheroesException ex) {

    res.setStatus(ex.getCode());
    return ResponseEntity.status(ex.getCode()).body(new ErrorDto(ex.getMessage()));
  }

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public static ResponseEntity<ErrorDto> handleMethodMismatchExceptions(
      HttpServletRequest req, MethodArgumentTypeMismatchException ex) {

    return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));

  }

  @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public static ResponseEntity<ErrorDto> handleMediaTypeNotSupportedExceptions(
      HttpServletRequest req, HttpMediaTypeNotSupportedException ex) {

    return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public static ResponseEntity<ErrorFieldDto> handleValidationExceptions(
      HttpServletRequest req, MethodArgumentNotValidException ex) {

    Map<String, String> errorList = new HashMap<>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(err -> errorList.put(err.getField(), err.getDefaultMessage()));

    return ResponseEntity.badRequest().body(new ErrorFieldDto(ex.getBody().getDetail(), errorList));
  }
}
