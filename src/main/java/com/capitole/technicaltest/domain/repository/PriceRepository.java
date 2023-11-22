package com.capitole.technicaltest.domain.repository;

import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.entity.Price;
import com.capitole.technicaltest.domain.model.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

    List<Price> findPriceWithHigherPriority(Brand brand, Product product, LocalDateTime date);
}
