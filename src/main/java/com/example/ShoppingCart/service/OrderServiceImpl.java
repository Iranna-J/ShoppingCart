package com.example.ShoppingCart.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.domain.*;
import com.example.ShoppingCart.dto.*;
import com.example.ShoppingCart.exception.InsufficientStockException;
import com.example.ShoppingCart.exception.OrderStatusNotMatchException;
import com.example.ShoppingCart.exception.UserRoleNotMatchException;
import com.example.ShoppingCart.exception.notFoundByIdException;
import com.example.ShoppingCart.repo.*;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private OrderItemRepo orderItemRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ProductRepo productRepo;

	@Override
	public ResponseEntity<ResponseStructure<OrderResponseDTO>> placeOrder(OrderRequestDTO orderRequest) {
	    Optional<User> customerOpt = userRepo.findById(orderRequest.getCustomerId());
	    if (customerOpt.isEmpty()) {
	        throw new notFoundByIdException("Customer not found with ID: " + orderRequest.getCustomerId());
	    }

	    User customer = customerOpt.get();
	    Order order = new Order();
	    order.setCustomer(customer);
	    order.setStatus(OrderStatus.PENDING);
	    order.setCreatedAt(LocalDateTime.now());
	    order.setUpdatedAt(LocalDateTime.now());

	    BigDecimal totalPrice = BigDecimal.ZERO;
	    List<OrderItem> orderItems = new ArrayList<>();

	    for (OrderItemRequestDTO itemDTO : orderRequest.getOrderItems()) {
	        Optional<Product> productOpt = productRepo.findById(itemDTO.getProductId());
	        if (productOpt.isEmpty()) {
	            throw new notFoundByIdException("Product not found with ID: " + itemDTO.getProductId());
	        }

	        Product product = productOpt.get();
	        if (product.getStockQuantity() < itemDTO.getQuantity()) {
	            throw new InsufficientStockException("Not enough stock for product: " + product.getName());
	        }

	        product.setStockQuantity(product.getStockQuantity() - itemDTO.getQuantity());
	        productRepo.save(product);

	        BigDecimal itemTotalPrice = product.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
	        totalPrice = totalPrice.add(itemTotalPrice);

	        OrderItem orderItem = new OrderItem();
	        orderItem.setOrder(order);
	        orderItem.setProduct(product);
	        orderItem.setQuantity(itemDTO.getQuantity());
	        orderItem.setPrice(itemTotalPrice);

	        orderItems.add(orderItem);
	    }

	    order.setOrderItems(orderItems);
	    order.setTotalPrice(totalPrice);

	    order = orderRepo.save(order);

	    OrderResponseDTO responseDTO = new OrderResponseDTO(order);

	    ResponseStructure<OrderResponseDTO> response = new ResponseStructure<>();
	    response.setStatus(HttpStatus.CREATED.value());
	    response.setMessage("Order placed successfully!");
	    response.setData(responseDTO);

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}


	@Override
	public List<OrderResponseDTO> getAllOrders() {
		return orderRepo.findAll().stream().map(OrderResponseDTO::new).collect(Collectors.toList());
	}

	@Override
	public OrderResponseDTO getOrderById(Long id) {
		Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
		return new OrderResponseDTO(order);
	}

	@Override
	public OrderResponseDTO updateOrderStatus(Long id, String status) {
		EnumSet<OrderStatus> orderStatus = EnumSet.of(OrderStatus.DELIVERED, OrderStatus.CANCELLED, OrderStatus.PENDING, OrderStatus.PROCESSING, OrderStatus.SHIPPED);

		Optional<Order> order = orderRepo.findById(id);
		if(order.isEmpty()) {
			throw new notFoundByIdException("Order not found!!!!");
		}
		
		if (!orderStatus.contains(status)) {
			throw new OrderStatusNotMatchException("Invalid Order Status!!!");
		}
		
		order.get().setStatus(OrderStatus.valueOf(status.toUpperCase()));
		orderRepo.save(order.get());
		return new OrderResponseDTO(order.get());
	}
}
