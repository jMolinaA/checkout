package com.retail_checkout.checkout.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.retail_checkout.checkout.dto.CartItemDTO;
import com.retail_checkout.checkout.dto.CartRequestDTO;
import com.retail_checkout.checkout.dto.CheckoutResponseDTO;
import com.retail_checkout.checkout.service.ICartService;
import com.retail_checkout.checkout.dto.DiscountDTO;
import com.retail_checkout.checkout.exception.ResourceNotFoundException;
import com.retail_checkout.checkout.model.Discount;
import com.retail_checkout.checkout.model.Product;
import com.retail_checkout.checkout.model.Promotion;
import com.retail_checkout.checkout.repository.impl.JsonDiscountRepository;
import com.retail_checkout.checkout.repository.impl.JsonProductRepository;
import com.retail_checkout.checkout.repository.impl.JsonPromotionRepository;

@Service
public class CartServiceImpl implements ICartService {

    private JsonProductRepository productRepository;
    private JsonDiscountRepository discountRepository;
    private JsonPromotionRepository promotionRepository;

    public CartServiceImpl(JsonProductRepository productRepository, JsonDiscountRepository discountRepository, JsonPromotionRepository promotionRepository) {
        this.productRepository = productRepository;
        this.discountRepository = discountRepository;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public CheckoutResponseDTO getCheckout(CartRequestDTO cartRequest) {

        BigDecimal subtotal = BigDecimal.ZERO;
        List<DiscountDTO> productsDiscounts = new ArrayList<DiscountDTO>();

        for (CartItemDTO item : cartRequest.items()) {
            Product product = productRepository.findBySku(item.sku()).orElseThrow(() -> new ResourceNotFoundException("Product with SKU " + item.sku() + " does not exists."));
            BigDecimal productTotal = product.price().multiply(new BigDecimal(item.quantity()));
            subtotal = subtotal.add(productTotal);

            Discount discount = discountRepository.findBySku(item.sku()).orElse(null);

            if (discount != null) {
                BigDecimal discountPrice = BigDecimal.ZERO;

                if (discount.type().equals("percentage")) {
                    discountPrice = productTotal.multiply(discount.value());
                } else {
                    discountPrice = discount.value().multiply(new BigDecimal(item.quantity()));
                }
                productsDiscounts.add(new DiscountDTO(discount.id(), discount.description(), discountPrice));
            }

            Promotion promotion = promotionRepository.findBySku(item.sku()).orElse(null);
            
            if (promotion != null) {
                BigDecimal discountPrice = BigDecimal.ZERO;

                if (promotion.minQty() <= item.quantity()) {
                    if (promotion.type().equals("percentage")) {
                        discountPrice = productTotal.multiply(promotion.value());
                    } else {
                        discountPrice = promotion.value().multiply(new BigDecimal(item.quantity()));
                    }
                    productsDiscounts.add(new DiscountDTO(promotion.id(), promotion.description(), discountPrice));
                }
            }
        }

        BigDecimal paymentMethodDiscountRate = BigDecimal.ZERO;

        if (cartRequest.paymentMethod().equals("DEBIT")) {
            paymentMethodDiscountRate = new BigDecimal(0.1);
        } else if (cartRequest.paymentMethod().equals("CREDIT")) {
            paymentMethodDiscountRate = new BigDecimal(0.1);
        } else if (cartRequest.paymentMethod().equals("CASH")) {
            paymentMethodDiscountRate = BigDecimal.ZERO;
        } else if (cartRequest.paymentMethod().equals("WALMART_CARD")) {
            paymentMethodDiscountRate = new BigDecimal(0.2);
        }
        
        BigDecimal total = subtotal;

        for (DiscountDTO discount : productsDiscounts) {
            total = total.subtract(discount.discount());
        }

        BigDecimal totalWithPaymentMethodDiscount = total.multiply(new BigDecimal(1).subtract(paymentMethodDiscountRate));

        CheckoutResponseDTO response = new CheckoutResponseDTO(
            subtotal,
            productsDiscounts,
            paymentMethodDiscountRate.setScale(2, RoundingMode.HALF_UP),
            total.setScale(0, RoundingMode.HALF_UP),
            totalWithPaymentMethodDiscount.setScale(0, RoundingMode.HALF_UP)
        );

        return response;
    }
}
