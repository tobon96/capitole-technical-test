package com.capitole.technicaltest.application.port.in;

import com.capitole.technicaltest.domain.model.entity.Price;
import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.entity.Product;

import java.time.LocalDateTime;

public interface GetPriceWithHigherPriorityQuery {

    Price execute(Brand brand, Product product, LocalDateTime date);
}
