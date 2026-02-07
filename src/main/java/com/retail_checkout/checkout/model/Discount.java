package com.retail_checkout.checkout.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Discount(
    String id,
    String description,
    String type,
    BigDecimal value,
    @JsonProperty("applies_to")
    List<String> appliesTo
) {}
