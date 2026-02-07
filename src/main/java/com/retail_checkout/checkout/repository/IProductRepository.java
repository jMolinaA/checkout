package com.retail_checkout.checkout.repository;

import java.util.Optional;

import com.retail_checkout.checkout.model.Product;

public interface IProductRepository {
    Optional<Product> findBySku(String sku);
}
