package com.capitole.technicaltest.unit.domain.model.entity;

import org.junit.jupiter.api.Test;

import static com.capitole.technicaltest.unit.factory.domain.PriceFactory.buildPrice;
import static com.capitole.technicaltest.unit.factory.domain.PriceFactory.buildPricesListWithSameId;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestPrice {

  @Test
  public void givenPriceDomainEntities_shouldValidateEquality() {
    var notEqualPriceOne = buildPrice();
    var notEqualPriceTwo = buildPrice();

    var equalPrices = buildPricesListWithSameId();
    assert equalPrices.get(0).equals(equalPrices.get(1));
    assertNotEquals(notEqualPriceOne, notEqualPriceTwo);
  }
}
