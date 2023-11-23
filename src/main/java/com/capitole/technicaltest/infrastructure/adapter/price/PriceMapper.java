package com.capitole.technicaltest.infrastructure.adapter.price;

import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.entity.Price;
import com.capitole.technicaltest.domain.model.entity.Product;
import com.capitole.technicaltest.domain.model.valueObject.Currency;
import com.capitole.technicaltest.domain.model.valueObject.DateRange;
import com.capitole.technicaltest.infrastructure.adapter.price.entity.PriceEntity;

public class PriceMapper {

    private PriceMapper() {

    }

    public static Price toDomain(PriceEntity priceEntity) {
        return Price.builder()
            .id(priceEntity.getId().toString())
            .brand(Brand.builder()
                .id(priceEntity.getBrand().getId())
                .name(priceEntity.getBrand().getName())
                .build())
            .product(Product.builder()
                .id(priceEntity.getProduct().getId())
                .build())
            .dateRange(DateRange.builder()
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .build())
            .currency(Currency.builder()
                .isoName(priceEntity.getCurrency())
                .amount(priceEntity.getPrice())
                .build())
            .priority(priceEntity.getPriority())
            .priceList(priceEntity.getPriceList())
            .build();
    }
}
