package com.capitole.technicaltest.infrastructure.controller;

import com.capitole.technicaltest.application.port.in.GetPriceWithHigherPriorityQuery;
import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.entity.Price;
import com.capitole.technicaltest.domain.model.entity.Product;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController {

    private final GetPriceWithHigherPriorityQuery query;

    public PriceController(GetPriceWithHigherPriorityQuery query) {
        this.query = query;
    }


    @GetMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public Price getPriceForDate(@RequestParam(name = "brand_id") Long brandId,
                                       @RequestParam(name = "product_id") Long productId,
                                       @RequestParam @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return query.execute(new Brand(brandId, null),
                new Product(productId),
                date).orElse(null);
    }
}
