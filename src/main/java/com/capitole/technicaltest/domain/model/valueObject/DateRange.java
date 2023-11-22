package com.capitole.technicaltest.domain.model.valueObject;

import java.time.LocalDateTime;

public record DateRange(LocalDateTime startDate, LocalDateTime endDate) {

  public static DateRange.Builder builder() {
    return new DateRange.Builder();
  }

  public static class Builder {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Builder startDate(LocalDateTime startDate) {
      this.startDate = startDate;
      return this;
    }

    public Builder endDate(LocalDateTime endDate) {
      this.endDate = endDate;
      return this;
    }

    public DateRange build() {
      return new DateRange(startDate, endDate);
    }
  }
}
