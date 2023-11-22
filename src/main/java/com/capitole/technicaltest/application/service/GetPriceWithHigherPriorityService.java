package com.capitole.technicaltest.application.service;

import com.capitole.technicaltest.application.port.in.GetPriceWithHigherPriorityQuery;
import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.aggregate.Price;
import com.capitole.technicaltest.domain.model.entity.Product;
import com.capitole.technicaltest.domain.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Service
public class GetPriceWithHigherPriorityService implements GetPriceWithHigherPriorityQuery {

    private final PriceRepository priceRepository;

    public GetPriceWithHigherPriorityService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<Price> execute(Brand brand, Product product, LocalDateTime date) {
        return priceRepository.findPriceWithHigherPriority(brand, product, date)
                .stream()
                .max(Comparator.comparingInt(Price::priority));
    }
}
