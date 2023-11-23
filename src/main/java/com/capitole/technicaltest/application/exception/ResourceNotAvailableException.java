package com.capitole.technicaltest.application.exception;

public class ResourceNotAvailableException extends RuntimeException{

  public final String ERROR_CODE = "resourceNotAvailable";

  public ResourceNotAvailableException(String message) {
    super(message);
  }
}
