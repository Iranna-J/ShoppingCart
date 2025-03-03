package com.example.ShoppingCart.dto;
import java.math.BigDecimal;

import com.example.ShoppingCart.domain.OrderItem;

public class OrderItemDTO {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;

    public OrderItemDTO(OrderItem item) {
        this.productId = item.getProduct().getId();
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
    }

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

    
}
