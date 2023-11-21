package com.capitole.technicaltest.infrastructure.controller;

import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.entity.Price;
import com.capitole.technicaltest.domain.model.entity.Product;
import com.capitole.technicaltest.domain.repository.PriceRepository;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
public class PriceController {

    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    private final PriceRepository priceRepository;

    public PriceController(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    @GetMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public Price getPriceForDate(@RequestParam(name = "brand_id") Long brandId,
                                 @RequestParam(name = "product_id") Long productId,
                                 @RequestParam @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        var price = priceRepository.findPriceWithHigherPriority(
                new Brand(brandId, null),
                new Product(productId),
                date
                );

        return price.stream().findAny().orElse(null);
    }
}
