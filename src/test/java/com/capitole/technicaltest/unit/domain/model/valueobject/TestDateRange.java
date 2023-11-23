package com.capitole.technicaltest.unit.domain.model.valueobject;

import com.capitole.technicaltest.domain.model.valueObject.DateRange;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TestDateRange {

  @Test
  void givenDateRanges_shouldValidateEquality() {
    var dateRangeOne = DateRange.builder()
        .startDate(LocalDateTime.of(2023, 11, 21, 10, 0, 0))
        .endDate(LocalDateTime.of(2023, 12, 31, 23, 59, 59))
        .build();
    var dateRangeTwo = DateRange.builder()
        .startDate(LocalDateTime.of(2023, 11, 21, 10, 0, 0))
        .endDate(LocalDateTime.of(2023, 12, 31, 23, 59, 59))
        .build();
    var dateRangeThree = DateRange.builder()
        .startDate(LocalDateTime.of(2023, 11, 21, 10, 0, 0))
        .endDate(LocalDateTime.of(2024, 12, 31, 23, 59, 59))
        .build();

    assertEquals(dateRangeOne, dateRangeTwo);
    assertNotEquals(dateRangeOne, dateRangeThree);
  }
}
