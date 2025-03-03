package com.example.ShoppingCart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.domain.Product;
import com.example.ShoppingCart.dto.ProductResponseDTO;
import com.example.ShoppingCart.service.ProductService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<ProductResponseDTO>> saveProduct(@RequestBody Product product){
		return productService.saveProduct(product);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<ProductResponseDTO>> getSingleProduct(@RequestParam long id){
		return productService.getProductById(id);
	}
	
	@GetMapping("/findall")
	public ResponseEntity<ResponseStructure<List<ProductResponseDTO>>> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<ResponseStructure<ProductResponseDTO>> updateProduct(@PathVariable long productId, @RequestBody Product updatedProduct){
		return productService.updateProduct(productId,updatedProduct);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<ProductResponseDTO>> deleteProduct(@RequestParam long id){
		return productService.deleteProduct(id);
	}
}
