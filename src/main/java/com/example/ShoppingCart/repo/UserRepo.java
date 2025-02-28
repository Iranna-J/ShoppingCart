package com.example.ShoppingCart.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.ShoppingCart.domain.User;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
	User findByEmail(String email);
}
