package com.example.ShoppingCart.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.dto.OrderRequestDTO;
import com.example.ShoppingCart.dto.OrderResponseDTO;

public interface OrderService {

	 ResponseEntity<ResponseStructure<OrderResponseDTO>> placeOrder(OrderRequestDTO orderRequest);

	List<OrderResponseDTO> getAllOrders();

	OrderResponseDTO getOrderById(Long id);

	OrderResponseDTO updateOrderStatus(Long id, String status);

}
