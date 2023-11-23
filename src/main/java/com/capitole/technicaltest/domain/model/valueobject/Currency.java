package com.capitole.technicaltest.domain.model.valueobject;

public record Currency(String isoName, Double amount) {

  public static Currency.Builder builder() {
    return new Currency.Builder();
  }

  public static class Builder {
    private String isoName;
    private Double amount;

    public Builder isoName(String isoName) {
      this.isoName = isoName;
      return this;
    }

    public Builder amount(Double amount) {
      this.amount = amount;
      return this;
    }

    public Currency build() {
      return new Currency(isoName, amount);
    }
  }
}
