package com.retail_checkout.checkout.repository;

import java.util.Optional;

import com.retail_checkout.checkout.model.Discount;

public interface IDiscountsRepository {
    Optional<Discount> findBySku(String sku);
}
