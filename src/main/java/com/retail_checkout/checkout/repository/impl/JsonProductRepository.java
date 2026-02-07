package com.retail_checkout.checkout.repository.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.retail_checkout.checkout.model.Product;
import com.retail_checkout.checkout.repository.IProductRepository;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Repository
public class JsonProductRepository implements IProductRepository {
    private final List<Product> database;

    public JsonProductRepository(ObjectMapper objectMapper) throws IOException {
        InputStream is = getClass().getResourceAsStream("/data/products.json");
        this.database = objectMapper.readValue(is, new TypeReference<List<Product>>() {});
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return database.stream()
            .filter(product -> product.sku().equals(sku))
            .findFirst();
    }
}
