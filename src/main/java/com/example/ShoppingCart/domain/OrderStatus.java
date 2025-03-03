package com.example.ShoppingCart.domain;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    PENDING,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED;
	
	@JsonCreator
    public static OrderStatus fromString(String value) {
        return Stream.of(OrderStatus.values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

    @JsonValue
    public String toJson() {
        return name();
    }
}
