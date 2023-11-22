package com.capitole.technicaltest.infrastructure.adapter.price;

import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.aggregate.Price;
import com.capitole.technicaltest.domain.model.entity.Product;
import com.capitole.technicaltest.domain.model.valueObject.Currency;
import com.capitole.technicaltest.domain.model.valueObject.DateRange;
import com.capitole.technicaltest.infrastructure.adapter.price.entity.PriceEntity;

public class PriceMapper {

    public static Price toDomain(PriceEntity priceEntity) {
        return new Price(priceEntity.getId().toString(),
                new Product(priceEntity.getProduct().getId()),
                new Brand(priceEntity.getBrand().getId(), priceEntity.getBrand().getName()),
                new DateRange(priceEntity.getStartDate(), priceEntity.getEndDate()),
                new Currency(priceEntity.getCurrency(), priceEntity.getPrice()),
                priceEntity.getPriority(),
                priceEntity.getPriceList());
    }
}
