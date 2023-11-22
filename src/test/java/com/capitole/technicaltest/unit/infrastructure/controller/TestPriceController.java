package com.capitole.technicaltest.unit.infrastructure.controller;

import com.capitole.technicaltest.application.port.in.GetPriceWithHigherPriorityQuery;
import com.capitole.technicaltest.infrastructure.controller.PriceController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static com.capitole.technicaltest.unit.factory.domain.PriceFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class TestPriceController {

    @Mock
    private GetPriceWithHigherPriorityQuery query;

    @InjectMocks
    private PriceController priceController;

    @Test
    public void givenBrandProductAndDateParameters_shouldReturnResponseWithPrice() {
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
        assertEquals(response.success(), true);
        assertEquals(response.data().brandId(), 1);
        assertEquals(response.error(), Collections.EMPTY_MAP);
    }
}
