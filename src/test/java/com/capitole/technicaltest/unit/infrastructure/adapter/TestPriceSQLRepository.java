package com.capitole.technicaltest.unit.infrastructure.adapter;

import com.capitole.technicaltest.infrastructure.adapter.price.PriceSQLRepository;
import com.capitole.technicaltest.infrastructure.persistence.PriceJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static com.capitole.technicaltest.unit.factory.domain.PriceFactory.*;
import static com.capitole.technicaltest.unit.factory.persistence.PriceEntityFactory.buildPriceEntities;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestPriceSQLRepository {

  @Mock
  private PriceJpaRepository jpaRepository;

  @InjectMocks
  private PriceSQLRepository repository;

  @Test
  void givenParameters_shouldReturnListOfDbEntities() {
    // Given
    var brand = buildParameterBrand();
    var product = buildParameterProduct();
    var expectedEntities =  buildPriceEntities();
    var date = LocalDateTime.of(2023, 12, 24, 10, 0, 0);
    when(jpaRepository.findByBrandIdAndProductIdAndDateParamBetweenRange(brand.id(), product.id(), date))
        .thenReturn(buildPriceEntities());

    // When
    var result = repository.findPriceWithHigherPriority(brand, product, date);

    // Then
    assertEquals(result, buildPricesList());
  }

}
