package com.example.ShoppingCart.dto;
import java.io.Serializable;
import java.math.BigDecimal;

import com.example.ShoppingCart.domain.Product;

public class ProductResponseDTO implements Serializable {  
    private static final long serialVersionUID = 1L;  

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private String category;
    private String imageUrl;
    private Long sellerId;

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stockQuantity = product.getStockQuantity();
        this.category = product.getCategory();
        this.imageUrl = product.getImageUrl();
        this.sellerId = product.getSeller().getId();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}
