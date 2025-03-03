package com.example.ShoppingCart.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.ShoppingCart.domain.Order;

@Repository
@EnableJpaRepositories
public interface OrderRepo extends JpaRepository<Order, Long>{

}
