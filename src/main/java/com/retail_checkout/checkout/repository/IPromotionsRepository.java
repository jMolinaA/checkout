package com.retail_checkout.checkout.repository;

import java.util.Optional;

import com.retail_checkout.checkout.model.Promotion;

public interface IPromotionsRepository {
    Optional<Promotion> findBySku(String sku);
}
