package com.capitole.technicaltest.infrastructure.controller.model;

import com.capitole.technicaltest.domain.model.aggregate.Price;

import java.time.LocalDateTime;

public record PriceResponse(Long productId,
                            Long brandId,
                            Integer priceList,
                            LocalDateTime startDate,
                            LocalDateTime endDate,
                            Double amount) {

  public static PriceResponse fromDomain(Price price) {
    return builder()
        .productId(price.product().id())
        .brandId(price.brand().id())
        .priceList(price.priceList())
        .startDate(price.dateRange().startDate())
        .endDate(price.dateRange().endDate())
        .amount(price.value().amount())
        .build();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Long productId;
    private Long brandId;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double amount;

    public Builder productId(Long productId) {
      this.productId = productId;
      return this;
    }

    public Builder brandId(Long brandId) {
      this.brandId = brandId;
      return this;
    }

    public Builder priceList(Integer priceList) {
      this.priceList = priceList;
      return this;
    }

    public Builder startDate(LocalDateTime startDate) {
      this.startDate = startDate;
      return this;
    }

    public Builder endDate(LocalDateTime endDate) {
      this.endDate = endDate;
      return this;
    }

    public Builder amount(Double amount) {
      this.amount = amount;
      return this;
    }

    public PriceResponse build() {
      return new PriceResponse(productId, brandId, priceList, startDate, endDate, amount);
    }
  }
}
