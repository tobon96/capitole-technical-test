package com.capitole.technicaltest.domain.model.entity;

import com.capitole.technicaltest.domain.model.valueobject.Currency;
import com.capitole.technicaltest.domain.model.valueobject.DateRange;

import java.util.Objects;

public record Price(String id,
                    Product product,
                    Brand brand,
                    DateRange dateRange,
                    Currency currency,
                    Integer priority,
                    Integer priceList) {

  public Boolean equals(Price price) {
    return Objects.equals(this.id, price.id());
  }

  public static Price.Builder builder() {
    return new Price.Builder();
  }

  public static class Builder {
    private String id;
    private Product product;
    private Brand brand;
    private DateRange dateRange;
    private Currency currency;
    private Integer priority;
    private Integer priceList;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder product(Product product) {
      this.product = product;
      return this;
    }

    public Builder brand(Brand brand) {
      this.brand = brand;
      return this;
    }

    public Builder dateRange(DateRange dateRange) {
      this.dateRange = dateRange;
      return this;
    }

    public Builder currency(Currency currency) {
      this.currency = currency;
      return this;
    }

    public Builder priority(Integer priority) {
      this.priority = priority;
      return this;
    }

    public Builder priceList(Integer priceList) {
      this.priceList = priceList;
      return this;
    }

    public Price build() {
      return new Price(id, product, brand, dateRange, currency, priority, priceList);
    }
  }
}
