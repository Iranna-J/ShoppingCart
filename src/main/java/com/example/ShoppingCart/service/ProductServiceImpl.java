package com.example.ShoppingCart.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<ResponseStructure<ProductResponseDTO>> saveProduct(Product product) {
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

		ProductResponseDTO productResponseDTO = new ProductResponseDTO(savedProduct);

		ResponseStructure<ProductResponseDTO> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Product added successfully!!!");
		responseStructure.setData(productResponseDTO);

		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<ProductResponseDTO>> getProductById(Long id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		if (optionalProduct.isPresent()) {
			ProductResponseDTO productResponseDTO = new ProductResponseDTO(optionalProduct.get());
			ResponseStructure<ProductResponseDTO> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Product found successfully!!!");
			responseStructure.setData(productResponseDTO);
			return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
		}
		throw new notFoundByIdException("Product not found!!!");
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ProductResponseDTO>>> getAllProducts() {
		List<Product> products = productRepo.findAll();

		List<ProductResponseDTO> productDTOs = products.stream().map(ProductResponseDTO::new)
				.collect(Collectors.toList());

		ResponseStructure<List<ProductResponseDTO>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Products retrieved successfully!!!");
		responseStructure.setData(productDTOs);

		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<ProductResponseDTO>> updateProduct(long productId, Product updatedProduct) {
		Optional<Product> existingProductOpt = productRepo.findById(productId);

		if (existingProductOpt.isEmpty()) {
			throw new notFoundByIdException("Product with ID " + productId + " not found!");
		}

		Product existingProduct = existingProductOpt.get();

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

		ResponseStructure<ProductResponseDTO> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Product updated successfully!!!");
		responseStructure.setData(new ProductResponseDTO(savedProduct));

		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<ProductResponseDTO>> deleteProduct(long id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		if (optionalProduct.isPresent()) {
			ProductResponseDTO productResponseDTO = new ProductResponseDTO(optionalProduct.get());
			productRepo.deleteById(id);
			ResponseStructure<ProductResponseDTO> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Product deleted successfully!!!");
			responseStructure.setData(productResponseDTO);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
		throw new notFoundByIdException("Product not found!!!");
	}
}
