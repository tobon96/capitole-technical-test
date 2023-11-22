package com.capitole.technicaltest.infrastructure.adapter.price;

import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.entity.Price;
import com.capitole.technicaltest.domain.model.entity.Product;
import com.capitole.technicaltest.domain.repository.PriceRepository;
import com.capitole.technicaltest.infrastructure.persistence.PriceJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PriceSQLRepository implements PriceRepository {

    private final PriceJpaRepository jpaRepository;

    public PriceSQLRepository(PriceJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Price> findPriceWithHigherPriority(Brand brand, Product product, LocalDateTime date) {
        return jpaRepository.findByBrandIdAndProductIdAndDateParamBetweenRange(brand.id(), product.id(), date)
                .stream()
                .map(PriceMapper::toDomain)
                .toList();
    }
}
