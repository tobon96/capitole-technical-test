package com.capitole.technicaltest.domain.model.aggregate;

import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.entity.Product;
import com.capitole.technicaltest.domain.model.valueObject.Currency;
import com.capitole.technicaltest.domain.model.valueObject.DateRange;

import java.util.UUID;

public record Price(String id,
                    Product product,
                    Brand brand,
                    DateRange dateRange,
                    Currency value,
                    Integer priority,
                    Integer priceList) {
}
