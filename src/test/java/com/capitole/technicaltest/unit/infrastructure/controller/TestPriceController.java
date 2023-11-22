package com.capitole.technicaltest.unit.infrastructure.controller;

import com.capitole.technicaltest.application.port.in.GetPriceWithHigherPriorityQuery;
import com.capitole.technicaltest.domain.model.aggregate.Price;
import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.entity.Product;
import com.capitole.technicaltest.domain.model.valueObject.Currency;
import com.capitole.technicaltest.domain.model.valueObject.DateRange;
import com.capitole.technicaltest.infrastructure.controller.PriceController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestPriceController {

    @Mock
    private GetPriceWithHigherPriorityQuery query;

    @InjectMocks
    private PriceController priceController;

    @Test
    public void givenBrandProductAndDateParameters_shouldReturnPriceThatApplies() {
        // Given
        var expectedPrice = buildPrice();
        when(query
            .execute(buildParameterBrand(),
                buildParamenterProduct(),
                LocalDateTime.of(2023, 11, 22, 10, 0, 0)))
            .thenReturn(expectedPrice);

        // When
        var response = priceController
            .getPriceForDate(1L,
                55555L,
                LocalDateTime.of(2023, 11, 22, 10, 0, 0));

        assertNotNull(response);
    }

    private Brand buildParameterBrand() {
        return new Brand(1L, null);
    }

    private Product buildParamenterProduct() {
        return new Product(55555L);
    }

    private Price buildPrice() {
        return new Price(UUID.randomUUID().toString(),
            new Product(55555L),
            new Brand(1L, "ZARA"),
            new DateRange(LocalDateTime.of(2023, 11, 21, 10, 0, 0),
                LocalDateTime.of(2023, 12, 31, 23, 59, 59)),
            new Currency("EUR", 55.0),
            1,
            1);
    }
}
