package com.capitole.technicaltest.infrastructure.adapter.out.persistence.price;

import com.capitole.technicaltest.infrastructure.adapter.out.persistence.price.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, UUID> {

    @Query("SELECT e FROM PriceEntity e " +
            "WHERE :brandId = e.brand.id " +
            "AND :productId = e.product.id " +
            "AND (:dateParam BETWEEN e.startDate AND e.endDate) ")
    List<PriceEntity> findByBrandIdAndProductIdAndDateParamBetweenRange(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("dateParam") LocalDateTime dateParam);
}
