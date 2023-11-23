package com.capitole.technicaltest.unit.factory.domain;

import com.capitole.technicaltest.domain.model.entity.Price;
import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.entity.Product;
import com.capitole.technicaltest.domain.model.valueobject.Currency;
import com.capitole.technicaltest.domain.model.valueobject.DateRange;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PriceFactory {

  public static Brand buildParameterBrand() {
    return Brand.builder()
        .id(1L)
        .name(null)
        .build();
  }

  public static Product buildParameterProduct() {
    return new Product(55555L);
  }

  public static Price buildPrice() {
    return Price.builder()
        .id(UUID.randomUUID().toString())
        .brand(Brand.builder()
            .id(1L)
            .name("ZARA")
            .build())
        .product(Product.builder()
            .id(55555L)
            .build())
        .dateRange(DateRange.builder()
            .startDate(LocalDateTime.of(2023, 11, 21, 10, 0, 0))
            .endDate(LocalDateTime.of(2023, 12, 31, 23, 59, 59))
            .build())
        .priority(1)
        .currency(Currency.builder()
            .amount(55.0)
            .isoName("EUR")
            .build())
        .build();
  }

  public static List<Price> buildPricesListWithSameId() {
    return List.of(
        Price.builder()
            .id("6fc03087-d265-11e7-b8c6-83e29cd24f4c")
            .brand(Brand.builder()
                .id(1L)
                .name("ZARA")
                .build())
            .product(Product.builder()
                .id(55555L)
                .build())
            .dateRange(DateRange.builder()
                .startDate(LocalDateTime.of(2023, 11, 21, 10, 0, 0))
                .endDate(LocalDateTime.of(2023, 12, 31, 23, 59, 59))
                .build())
            .priority(1)
            .priceList(1)
            .currency(Currency.builder()
                .amount(55.0)
                .isoName("EUR")
                .build())
            .build(),
        Price.builder()
            .id("6fc03087-d265-11e7-b8c6-83e29cd24f4c")
            .brand(Brand.builder()
                .id(2L)
                .name("BERSHKA")
                .build())
            .product(Product.builder()
                .id(55555L)
                .build())
            .dateRange(DateRange.builder()
                .startDate(LocalDateTime.of(2023, 12, 15, 10, 0, 0))
                .endDate(LocalDateTime.of(2024, 1, 31, 23, 59, 59))
                .build())
            .priority(2)
            .priceList(2)
            .currency(Currency.builder()
                .amount(50.0)
                .isoName("USD")
                .build())
            .build()
    );
  }

  public static List<Price> buildPricesList() {
    return List.of(
        Price.builder()
            .id("5fc03087-d265-11e7-b8c6-83e29cd24f4c")
            .brand(Brand.builder()
                .id(1L)
                .name("ZARA")
                .build())
            .product(Product.builder()
                .id(55555L)
                .build())
            .dateRange(DateRange.builder()
                .startDate(LocalDateTime.of(2023, 11, 21, 10, 0, 0))
                .endDate(LocalDateTime.of(2023, 12, 31, 23, 59, 59))
                .build())
            .priority(1)
            .priceList(1)
            .currency(Currency.builder()
                .amount(55.0)
                .isoName("EUR")
                .build())
            .build(),
        Price.builder()
            .id("6fc03087-d265-11e7-b8c6-83e29cd24f4c")
            .brand(Brand.builder()
                .id(1L)
                .name("ZARA")
                .build())
            .product(Product.builder()
                .id(55555L)
                .build())
            .dateRange(DateRange.builder()
                .startDate(LocalDateTime.of(2023, 12, 15, 10, 0, 0))
                .endDate(LocalDateTime.of(2024, 1, 31, 23, 59, 59))
                .build())
            .priority(2)
            .priceList(2)
            .currency(Currency.builder()
                .amount(50.0)
                .isoName("EUR")
                .build())
            .build()
    );
  }
}
