package com.capitole.technicaltest.unit.application.service;

import com.capitole.technicaltest.application.service.GetPriceWithHigherPriorityService;
import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.aggregate.Price;
import com.capitole.technicaltest.domain.model.entity.Product;
import com.capitole.technicaltest.domain.model.valueObject.Currency;
import com.capitole.technicaltest.domain.model.valueObject.DateRange;
import com.capitole.technicaltest.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestGetPriceWithHigherPriorityService {

  @Mock
  private PriceRepository priceRepository;

  @InjectMocks
  private GetPriceWithHigherPriorityService service;

  @Test
  public void given_shouldReturnPriceWithHigherPriority() {
    // Given
    var expectedPrice = buildExpectedPrice();
    when(priceRepository
        .findPriceWithHigherPriority(buildParameterBrand(),
            buildParamenterProduct(),
            LocalDateTime.of(2023, 12, 24, 10, 0, 0)))
        .thenReturn(buildPricesList());

    // When
    var actualPriceWithHigherPriority = service.execute(buildParameterBrand(),
        buildParamenterProduct(),
        LocalDateTime.of(2023, 12, 24, 10, 0, 0));

    // Then
    assertEquals(expectedPrice.priority(), actualPriceWithHigherPriority.priority());
    assertEquals(expectedPrice.value(), actualPriceWithHigherPriority.value());
  }

  private Brand buildParameterBrand() {
    return new Brand(1L, null);
  }

  private Product buildParamenterProduct() {
    return new Product(55555L);
  }

  private List<Price> buildPricesList() {
    return List.of(
        new Price(UUID.randomUUID().toString(),
            new Product(55555L),
            new Brand(1L, "ZARA"),
            new DateRange(LocalDateTime.of(2023, 11, 21, 10, 0, 0),
                LocalDateTime.of(2023, 12, 31, 23, 59, 59)),
            new Currency("EUR", 55.0),
            1,
            1),
        new Price(UUID.randomUUID().toString(),
            new Product(55555L),
            new Brand(1L, "ZARA"),
            new DateRange(LocalDateTime.of(2023, 12, 15, 10, 0, 0),
                LocalDateTime.of(2024, 1, 31, 23, 59, 59)),
            new Currency("EUR", 50.0),
            2,
            2)
    );
  }

  private Price buildExpectedPrice() {
    return new Price(UUID.randomUUID().toString(),
        new Product(55555L),
        new Brand(1L, "ZARA"),
        new DateRange(LocalDateTime.of(2023, 12, 15, 10, 0, 0),
            LocalDateTime.of(2024, 1, 31, 23, 59, 59)),
        new Currency("EUR", 50.0),
        2,
        2);
  }

}
