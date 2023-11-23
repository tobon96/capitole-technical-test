package com.capitole.technicaltest.application.exception;

public class PriceNotAvailableException extends ResourceNotAvailableException {
  public PriceNotAvailableException() {
    super("Price is not available");
  }
}
