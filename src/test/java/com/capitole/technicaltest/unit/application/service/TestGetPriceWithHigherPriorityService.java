package com.capitole.technicaltest.unit.application.service;

import com.capitole.technicaltest.application.exception.ResourceNotAvailableException;
import com.capitole.technicaltest.application.port.out.PriceRepository;
import com.capitole.technicaltest.application.service.GetPriceWithHigherPriorityService;
import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.entity.Price;
import com.capitole.technicaltest.domain.model.entity.Product;
import com.capitole.technicaltest.domain.model.valueobject.Currency;
import com.capitole.technicaltest.domain.model.valueobject.DateRange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.capitole.technicaltest.unit.factory.domain.PriceFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestGetPriceWithHigherPriorityService {

  @Mock
  private PriceRepository priceRepository;

  @Spy
  private Logger logger;

  @InjectMocks
  private GetPriceWithHigherPriorityService service;

  @Test
  void givenExecute_shouldReturnPriceWithHigherPriority() {
    // Given
    var brand = buildParameterBrand();
    var product = buildParameterProduct();
    var date = LocalDateTime.of(2023, 12, 24, 10, 0, 0);
    var expectedPrice = buildExpectedPrice();
    when(priceRepository
        .findPriceWithHigherPriority(brand, product, date))
        .thenReturn(buildPricesList());

    // When
    var actualPriceWithHigherPriority = service.execute(brand, product, date);

    // Then
    assertEquals(expectedPrice.priority(), actualPriceWithHigherPriority.priority());
    assertEquals(expectedPrice.currency(), actualPriceWithHigherPriority.currency());
  }

  @Test
  void givenExecute_shouldThrowException() {
    // Given
    var brand = buildParameterBrand();
    var product = buildParameterProduct();
    var date = LocalDateTime.of(2024, 2, 24, 10, 0, 0);
    when(priceRepository
        .findPriceWithHigherPriority(brand, product, date))
        .thenReturn(List.of());

    // When
    var exception = assertThrows(ResourceNotAvailableException.class, () -> service.execute(brand, product, date));

    // Then
    assertEquals("Price is not available", exception.getMessage());
    verify(logger, times(1))
        .error("Price not available for parameters {}, {}, {}", brand.id(), product.id(), date);
  }

  private Price buildExpectedPrice() {
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
            .startDate(LocalDateTime.of(2023, 12, 15, 10, 0, 0))
            .endDate(LocalDateTime.of(2024, 1, 31, 23, 59, 59))
            .build())
        .priority(2)
        .priceList(2)
        .currency(Currency.builder()
            .amount(50.0)
            .isoName("EUR")
            .build())
        .build();
  }
}
