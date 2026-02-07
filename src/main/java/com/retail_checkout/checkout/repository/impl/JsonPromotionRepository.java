package com.retail_checkout.checkout.repository.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.retail_checkout.checkout.model.Promotion;
import com.retail_checkout.checkout.repository.IPromotionsRepository;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Repository
public class JsonPromotionRepository implements IPromotionsRepository {
    private final List<Promotion> database;

    public JsonPromotionRepository(ObjectMapper objectMapper) throws IOException {
        InputStream is = getClass().getResourceAsStream("/data/promotions.json");
        this.database = objectMapper.readValue(is, new TypeReference<List<Promotion>>() {});
    }

    @Override
    public Optional<Promotion> findBySku(String sku) {
        return database.stream()
            .filter(promotion -> promotion.sku().equals(sku))
            .findFirst();
    }
}