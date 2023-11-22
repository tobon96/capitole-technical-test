package com.capitole.technicaltest.domain.model.entity;

public record Brand(Long id, String name) {

  public static Brand.Builder builder() {
    return new Brand.Builder();
  }

  public static class Builder {
    private Long id;
    private String name;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Brand build() {
      return new Brand(id, name);
    }
  }
}
