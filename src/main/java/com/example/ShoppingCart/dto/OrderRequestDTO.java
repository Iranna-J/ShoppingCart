package com.example.ShoppingCart.dto;

import java.util.List;

public class OrderRequestDTO {
    private Long customerId;
    private List<OrderItemRequestDTO> orderItems;

    // Constructors
    public OrderRequestDTO() {}

    public OrderRequestDTO(Long customerId, List<OrderItemRequestDTO> orderItems) {
        this.customerId = customerId;
        this.orderItems = orderItems;
    }

    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemRequestDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemRequestDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
