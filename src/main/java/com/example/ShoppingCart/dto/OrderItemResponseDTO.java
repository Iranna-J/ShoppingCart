package com.example.ShoppingCart.dto;

import java.math.BigDecimal;

import com.example.ShoppingCart.domain.OrderItem;

public class OrderItemResponseDTO {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;

    public OrderItemResponseDTO(OrderItem orderItem) {
        if (orderItem != null && orderItem.getProduct() != null) {
            this.productId = orderItem.getProduct().getId();
            this.quantity = orderItem.getQuantity();
            this.price = orderItem.getPrice();
        }
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
