package com.capitole.technicaltest.application.service;

import com.capitole.technicaltest.application.port.in.GetPriceWithHigherPriorityQuery;
import com.capitole.technicaltest.domain.model.aggregate.Price;
import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.entity.Product;
import com.capitole.technicaltest.domain.repository.PriceRepository;
import com.capitole.technicaltest.infrastructure.exception.PriceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
public class GetPriceWithHigherPriorityService implements GetPriceWithHigherPriorityQuery {

    private final PriceRepository priceRepository;

    public GetPriceWithHigherPriorityService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price execute(Brand brand, Product product, LocalDateTime date) {
        return priceRepository.findPriceWithHigherPriority(brand, product, date)
            .stream()
            .max(Comparator.comparingInt(Price::priority))
            .orElseThrow(PriceNotFoundException::new);
    }
}
