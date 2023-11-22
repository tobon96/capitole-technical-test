package com.capitole.technicaltest.domain.model.entity;

public record Product(Long id) {

  public static Product.Builder builder() {
    return new Product.Builder();
  }

  public static class Builder {
    private Long id;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Product build() {
      return new Product(id);
    }
  }
}
