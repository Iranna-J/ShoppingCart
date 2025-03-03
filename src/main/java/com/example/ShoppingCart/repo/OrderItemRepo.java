package com.example.ShoppingCart.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.ShoppingCart.domain.OrderItem;

@Repository
@EnableJpaRepositories
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> { }
