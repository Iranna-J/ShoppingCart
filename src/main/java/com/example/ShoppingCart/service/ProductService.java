package com.example.ShoppingCart.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.domain.Product;
import com.example.ShoppingCart.dto.ProductResponseDTO;

public interface ProductService {

	ResponseEntity<ResponseStructure<ProductResponseDTO>> saveProduct(Product product);

	ResponseEntity<ResponseStructure<ProductResponseDTO>> getProductById(Long id);

	ResponseEntity<ResponseStructure<List<ProductResponseDTO>>> getAllProducts();

	ResponseEntity<ResponseStructure<ProductResponseDTO>> updateProduct(long productId, Product updatedProduct);

	ResponseEntity<ResponseStructure<ProductResponseDTO>> deleteProduct(long id);

}
