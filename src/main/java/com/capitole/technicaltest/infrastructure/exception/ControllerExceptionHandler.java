package com.capitole.technicaltest.infrastructure.exception;

import com.capitole.technicaltest.infrastructure.controller.model.Error;
import com.capitole.technicaltest.infrastructure.controller.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Response<Object, Error> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
    var errorMessage = String.format("Invalid argument type: %s should be %s",
        ex.getName(),
        Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
    return Response.error(
        Error.builder()
            .code(ex.getErrorCode())
            .error(ex.getClass().getSimpleName())
            .cause(errorMessage)
            .build());
  }

  @ExceptionHandler(value = {Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response<Object, Error> handleUncaughtException(final Exception ex) {
    return Response.error(
        Error.builder()
            .code("uncaughtException")
            .error(ex.getClass().getSimpleName())
            .cause(ex.getMessage())
            .build());
  }

  @ExceptionHandler(value = {PriceNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Response<Object, Error> handlePriceNotFoundException(final PriceNotFoundException ex) {
    return Response.error(
        Error.builder()
            .code(ex.ERROR_CODE)
            .error(ex.getClass().getSimpleName())
            .cause("Incorrect parameters: Entity Not found")
            .build());
  }
}
