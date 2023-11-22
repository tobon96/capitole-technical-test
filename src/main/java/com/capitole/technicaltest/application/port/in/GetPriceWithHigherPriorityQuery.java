package com.capitole.technicaltest.application.port.in;

import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.aggregate.Price;
import com.capitole.technicaltest.domain.model.entity.Product;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetPriceWithHigherPriorityQuery {

    Price execute(Brand brand, Product product, LocalDateTime date);
}