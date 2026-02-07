package com.retail_checkout.checkout.dto;

import lombok.Builder;

@Builder
public record ShippingAddressDTO(
    String street,
    String city,
    String zoneId
) {}