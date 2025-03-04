package com.example.ShoppingCart.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.domain.Product;
import com.example.ShoppingCart.dto.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO saveProduct(Product product);

    ProductResponseDTO getProductById(Long id);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO updateProduct(long productId, Product updatedProduct);

    ProductResponseDTO deleteProduct(long id);
}
