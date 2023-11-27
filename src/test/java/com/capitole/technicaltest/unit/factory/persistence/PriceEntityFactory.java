package com.capitole.technicaltest.unit.factory.persistence;

import com.capitole.technicaltest.infrastructure.adapter.out.persistence.price.entity.BrandEntity;
import com.capitole.technicaltest.infrastructure.adapter.out.persistence.price.entity.PriceEntity;
import com.capitole.technicaltest.infrastructure.adapter.out.persistence.price.entity.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PriceEntityFactory {

  public static List<PriceEntity> buildPriceEntities() {
    return List.of(
        new PriceEntity(
            UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c"),
            new BrandEntity(1L, "ZARA"),
            LocalDateTime.of(2023, 11, 21, 10, 0, 0),
            LocalDateTime.of(2023, 12, 31, 23, 59, 59),
            new ProductEntity(55555L),
            1,
            55.0,
            "EUR",
            1
        ),
        new PriceEntity(
            UUID.fromString("6fc03087-d265-11e7-b8c6-83e29cd24f4c"),
            new BrandEntity(1L, "ZARA"),
            LocalDateTime.of(2023, 12, 15, 10, 0, 0),
            LocalDateTime.of(2024, 1, 31, 23, 59, 59),
            new ProductEntity(55555L),
            2,
            50.0,
            "EUR",
            2
        ));
  }
}
