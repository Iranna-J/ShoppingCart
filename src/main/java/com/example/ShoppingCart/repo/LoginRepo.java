package com.example.ShoppingCart.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.ShoppingCart.domain.User;

@Repository
@EnableJpaRepositories
public interface LoginRepo extends JpaRepository<User, Long>{
	User findByEmail(String email);
	Optional<User> findOneByEmailAndPassword(String email, String password);
}
