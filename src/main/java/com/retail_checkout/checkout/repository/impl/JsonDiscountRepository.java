package com.retail_checkout.checkout.repository.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.retail_checkout.checkout.model.Discount;
import com.retail_checkout.checkout.repository.IDiscountsRepository;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Repository
public class JsonDiscountRepository implements IDiscountsRepository {
    private final List<Discount> database;

    public JsonDiscountRepository(ObjectMapper objectMapper) throws IOException {
        InputStream is = getClass().getResourceAsStream("/data/discounts.json");
        this.database = objectMapper.readValue(is, new TypeReference<List<Discount>>() {});
    }

    @Override
    public Optional<Discount> findBySku(String sku) {
        return database.stream()
            .filter(discount -> discount.appliesTo().contains(sku))
            .findFirst();
    }
}
