package com.retail_checkout.checkout.model;

import java.math.BigDecimal;

public record Promotion(
    String id,
    String description,
    String type,
    String sku,
    Integer minQty,
    BigDecimal value
) {}
