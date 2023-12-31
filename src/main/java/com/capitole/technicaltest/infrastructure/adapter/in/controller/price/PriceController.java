package com.capitole.technicaltest.infrastructure.adapter.in.controller.price;

import com.capitole.technicaltest.application.port.in.GetPriceWithHigherPriorityQuery;
import com.capitole.technicaltest.domain.model.entity.Brand;
import com.capitole.technicaltest.domain.model.entity.Product;
import com.capitole.technicaltest.infrastructure.adapter.in.controller.price.model.PriceResponse;
import com.capitole.technicaltest.infrastructure.adapter.in.controller.price.model.Response;
import com.capitole.technicaltest.infrastructure.adapter.in.controller.price.model.Error;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController {

    private final GetPriceWithHigherPriorityQuery query;

    public PriceController(GetPriceWithHigherPriorityQuery query) {
        this.query = query;
    }

    @GetMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response<PriceResponse, Error> getPriceForDate(@RequestParam(name = "brand_id") Long brandId,
                                                          @RequestParam(name = "product_id") Long productId,
                                                          @RequestParam @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        var result = query.execute(Brand.builder().id(brandId).build(),
            Product.builder().id(productId).build(),
            date);
        var response = PriceResponse.fromDomain(result);
        return Response.ok(response);
    }
}
