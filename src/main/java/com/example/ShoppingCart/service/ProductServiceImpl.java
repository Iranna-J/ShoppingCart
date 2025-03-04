package com.example.ShoppingCart.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.domain.Product;
import com.example.ShoppingCart.domain.User;
import com.example.ShoppingCart.dto.ProductResponseDTO;
import com.example.ShoppingCart.exception.ProductAlreadyExistException;
import com.example.ShoppingCart.exception.UserNotFoundException;
import com.example.ShoppingCart.exception.notFoundByIdException;
import com.example.ShoppingCart.repo.ProductRepo;
import com.example.ShoppingCart.repo.UserRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	@CachePut(value = "products", key = "#product.seller.id")
	@CacheEvict(value = "productList", allEntries = true)
	public ProductResponseDTO saveProduct(Product product) {
		Optional<User> user = userRepo.findById(product.getSeller().getId());
		if (user.isEmpty()) {
			throw new UserNotFoundException("Seller doesn't exist!!!");
		}
		product.setSeller(user.get());

		Optional<Product> existingProduct = productRepo.findByName(product.getName());
		if (existingProduct.isPresent()) {
			throw new ProductAlreadyExistException("Product with name '" + product.getName() + "' already exists!");
		}

		Product savedProduct = productRepo.save(product);
		return new ProductResponseDTO(savedProduct); // ✅ Cache only the DTO
	}

	@Override
	@Cacheable(value = "products", key = "#id")
	public ProductResponseDTO getProductById(Long id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		if (optionalProduct.isPresent()) {
			return new ProductResponseDTO(optionalProduct.get());
		}
		throw new notFoundByIdException("Product not found!!!");
	}

	@Override
	@Cacheable(value = "productList") // ✅ Cache only the data, not ResponseEntity
	public List<ProductResponseDTO> getAllProducts() {
	    List<Product> products = productRepo.findAll();
	    return products.stream()
	            .map(ProductResponseDTO::new)
	            .collect(Collectors.toList());
	}


	@Override
	@CachePut(value = "products", key = "#productId")
	@CacheEvict(value = "productList", allEntries = true)
	public ProductResponseDTO updateProduct(long productId, Product updatedProduct) {
		Optional<Product> existingProductOpt = productRepo.findById(productId);
		if (existingProductOpt.isEmpty()) {
			throw new notFoundByIdException("Product with ID " + productId + " not found!");
		}

		Product existingProduct = existingProductOpt.get();
		Optional<Product> productWithSameName = productRepo.findByName(updatedProduct.getName());
		if (productWithSameName.isPresent() && !productWithSameName.get().getId().equals(productId)) {
			throw new ProductAlreadyExistException(
					"Product with name '" + updatedProduct.getName() + "' already exists!");
		}

		Optional<User> sellerOpt = userRepo.findById(updatedProduct.getSeller().getId());
		if (sellerOpt.isEmpty()) {
			throw new UserNotFoundException("Seller doesn't exist!!!");
		}

		existingProduct.setName(updatedProduct.getName());
		existingProduct.setDescription(updatedProduct.getDescription());
		existingProduct.setPrice(updatedProduct.getPrice());
		existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
		existingProduct.setCategory(updatedProduct.getCategory());
		existingProduct.setImageUrl(updatedProduct.getImageUrl());
		existingProduct.setSeller(sellerOpt.get());
		existingProduct.setUpdatedAt(updatedProduct.getUpdatedAt());

		Product savedProduct = productRepo.save(existingProduct);
		return new ProductResponseDTO(savedProduct);
	}

	@Override
	@CacheEvict(value = { "products", "productList" }, allEntries = true)
	public ProductResponseDTO deleteProduct(long id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		if (optionalProduct.isPresent()) {
			ProductResponseDTO productResponseDTO = new ProductResponseDTO(optionalProduct.get());

			productRepo.deleteById(id);
			return new ProductResponseDTO(optionalProduct.get());
		}
		throw new notFoundByIdException("Product not found!!!");
	}

}
