package com.capitole.technicaltest.unit.domain.model.valueobject;

import com.capitole.technicaltest.domain.model.valueObject.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestCurrency {

  @Test
  public void givenCurrencyValueObjects_shouldValidateEquality() {
    var currencyEurOne = Currency.builder().amount(55.0).isoName("EUR").build();
    var currencyEurTwo = Currency.builder().amount(55.0).isoName("EUR").build();
    var currencyUsdOne = Currency.builder().amount(55.0).isoName("USD").build();

    assertEquals(currencyEurOne, currencyEurTwo);
    assertNotEquals(currencyEurOne, currencyUsdOne);
  }
}
