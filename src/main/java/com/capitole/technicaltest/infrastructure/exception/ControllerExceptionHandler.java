package com.capitole.technicaltest.infrastructure.exception;

import com.capitole.technicaltest.application.exception.ResourceNotAvailableException;
import com.capitole.technicaltest.infrastructure.adapter.in.controller.price.model.Error;
import com.capitole.technicaltest.infrastructure.adapter.in.controller.price.model.Response;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

@RestControllerAdvice
public class ControllerExceptionHandler {

  private final Logger logger;

  public ControllerExceptionHandler(Logger logger) {
    this.logger = logger;
  }

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
    logger.error("Uncaught exception", ex);
    return Response.error(
        Error.builder()
            .code("uncaughtException")
            .error(ex.getClass().getSimpleName())
            .cause(ex.getMessage())
            .build());
  }

  @ExceptionHandler(value = {ResourceNotAvailableException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Response<Object, Error> handlePriceNotFoundException(final ResourceNotAvailableException ex) {
    return Response.error(
        Error.builder()
            .code(ResourceNotAvailableException.ERROR_CODE)
            .error(ex.getClass().getSimpleName())
            .cause("Incorrect parameters: Entity not available")
            .build());
  }
}
