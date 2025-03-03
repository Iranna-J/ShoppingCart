package com.example.ShoppingCart.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ShoppingCart.domain.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
