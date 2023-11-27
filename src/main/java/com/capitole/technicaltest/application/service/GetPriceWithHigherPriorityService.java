package com.capitole.technicaltest.application.service;

import com.capitole.technicaltest.application.exception.PriceNotAvailableException;
import com.capitole.technicaltest.application.port.in.GetPriceWithHigherPriorityQuery;
import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.entity.Price;
import com.capitole.technicaltest.domain.model.entity.Product;
import com.capitole.technicaltest.application.port.out.PriceRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
public class GetPriceWithHigherPriorityService implements GetPriceWithHigherPriorityQuery {

    private final PriceRepository priceRepository;
    private final Logger logger;

    public GetPriceWithHigherPriorityService(PriceRepository priceRepository, Logger logger) {
        this.priceRepository = priceRepository;
        this.logger = logger;
    }

    @Override
    public Price execute(Brand brand, Product product, LocalDateTime date) {
        return priceRepository.findPriceWithHigherPriority(brand, product, date)
            .stream()
            .max(Comparator.comparingInt(Price::priority))
            .orElseThrow(() -> {
                logger.error("Price not available for parameters {}, {}, {}", brand.id(), product.id(), date);
                return new PriceNotAvailableException();
            });
    }
}
