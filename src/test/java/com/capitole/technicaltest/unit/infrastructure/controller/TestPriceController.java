package com.capitole.technicaltest.unit.infrastructure.controller;

import com.capitole.technicaltest.application.port.in.GetPriceWithHigherPriorityQuery;
import com.capitole.technicaltest.infrastructure.controller.PriceController;
import com.capitole.technicaltest.infrastructure.controller.model.Error;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static com.capitole.technicaltest.unit.factory.domain.PriceFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestPriceController {

    @Mock
    private GetPriceWithHigherPriorityQuery query;

    @InjectMocks
    private PriceController priceController;

    @Test
    void givenBrandProductAndDateParameters_shouldReturnResponseWithPrice() {
        // Given
        var expectedPrice = buildPrice();
        when(query
            .execute(buildParameterBrand(),
                buildParameterProduct(),
                LocalDateTime.of(2023, 11, 22, 10, 0, 0)))
            .thenReturn(expectedPrice);

        // When
        var response = priceController
            .getPriceForDate(1L,
                55555L,
                LocalDateTime.of(2023, 11, 22, 10, 0, 0));

        // Then
        assertNotNull(response);
        assertEquals(true, response.success());
        assertEquals(1, response.data().brandId());
        assertEquals(Error.builder().build(), response.error());
    }
}
