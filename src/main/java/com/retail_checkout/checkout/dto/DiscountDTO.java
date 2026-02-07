package com.retail_checkout.checkout.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record DiscountDTO(
    String discountId,
    String discountName,
    BigDecimal discount
) {}
