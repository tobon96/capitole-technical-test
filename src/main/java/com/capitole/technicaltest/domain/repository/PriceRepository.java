package com.capitole.technicaltest.domain.repository;

import com.capitole.technicaltest.domain.model.entity.Price;

import java.util.Optional;

public interface PriceRepository {

    Optional<Price> findPriceWithHigherPriority();
}
