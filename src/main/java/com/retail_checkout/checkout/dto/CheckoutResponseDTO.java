package com.retail_checkout.checkout.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

@Builder
public record CheckoutResponseDTO(
    BigDecimal subtotal,
    List<DiscountDTO> productsDiscounts,
    BigDecimal paymentMethodDiscountRate,
    BigDecimal total,
    BigDecimal totalWithPaymentMethodDiscount
) {}