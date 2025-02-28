package com.example.ShoppingCart.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

public enum Role {
	ADMIN,
	SELLER,
	CUSTOMER;
	
	@JsonCreator
    public static Role fromString(String value) {
        return Stream.of(Role.values())
                .filter(role -> role.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Role: " + value));
    }

    @JsonValue
    public String toJson() {
        return name();
    }
}
