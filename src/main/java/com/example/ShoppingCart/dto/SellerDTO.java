package com.example.ShoppingCart.dto;

import com.example.ShoppingCart.domain.User;

public class SellerDTO {
    private Long id;
    private String name;
    private String email;
    private Long mobile;

    public SellerDTO(User seller) {
        this.id = seller.getId();
        this.name = seller.getName();
        this.email = seller.getEmail();
        this.mobile = seller.getMobile();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
    
}
