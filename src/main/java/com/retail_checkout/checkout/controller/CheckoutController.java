package com.retail_checkout.checkout.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail_checkout.checkout.dto.CartRequestDTO;
import com.retail_checkout.checkout.dto.CheckoutResponseDTO;
import com.retail_checkout.checkout.service.ICartService;

@RestController
@RequestMapping("/api/v1/checkout")
public class CheckoutController {

    private final ICartService cartService;

    public CheckoutController(ICartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("")
    public ResponseEntity<CheckoutResponseDTO> cartCheckout(@RequestBody CartRequestDTO cartRequest) {
        ResponseEntity<CheckoutResponseDTO> response = ResponseEntity.ok(cartService.getCheckout(cartRequest));
        return response;
    }
}
