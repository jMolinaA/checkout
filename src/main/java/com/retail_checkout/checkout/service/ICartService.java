package com.retail_checkout.checkout.service;

import com.retail_checkout.checkout.dto.CartRequestDTO;
import com.retail_checkout.checkout.dto.CheckoutResponseDTO;

public interface ICartService {
    CheckoutResponseDTO getCheckout(CartRequestDTO cartRequest);
}
