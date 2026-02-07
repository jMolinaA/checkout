package com.retail_checkout.checkout.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record CartRequestDTO(
    String cartId,
    List<CartItemDTO> items,
    ShippingAddressDTO shippingAddress,
    String paymentMethod
) {}