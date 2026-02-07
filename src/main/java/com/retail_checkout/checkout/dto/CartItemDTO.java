package com.retail_checkout.checkout.dto;

import lombok.Builder;

@Builder
public record CartItemDTO(
    String sku,
    Integer quantity
) {}
