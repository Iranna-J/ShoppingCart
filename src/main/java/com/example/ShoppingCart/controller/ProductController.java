package com.example.ShoppingCart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.domain.Product;
import com.example.ShoppingCart.dto.ProductResponseDTO;
import com.example.ShoppingCart.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseStructure<ProductResponseDTO>> addProduct(@RequestBody Product product) {
        ProductResponseDTO productResponseDTO = productService.saveProduct(product);

        ResponseStructure<ProductResponseDTO> responseStructure = new ResponseStructure<>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("Product added successfully!!!");
        responseStructure.setData(productResponseDTO);

        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<ProductResponseDTO>> getProduct(@PathVariable Long id) {
        ProductResponseDTO productDTO = productService.getProductById(id);

        ResponseStructure<ProductResponseDTO> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Product retrieved successfully");
        response.setData(productDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/findall")
    public ResponseEntity<ResponseStructure<List<ProductResponseDTO>>> getAllProducts() {
        List<ProductResponseDTO> productList = productService.getAllProducts();

        ResponseStructure<List<ProductResponseDTO>> responseStructure = new ResponseStructure<>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Products retrieved successfully!!!");
        responseStructure.setData(productList);

        return ResponseEntity.ok(responseStructure);
    }


    @PutMapping("/{productId}")
    public ResponseEntity<ResponseStructure<ProductResponseDTO>> updateProduct(@PathVariable long productId, @RequestBody Product updatedProduct) {
        ProductResponseDTO productDTO = productService.updateProduct(productId, updatedProduct);

        ResponseStructure<ProductResponseDTO> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Product updated successfully");
        response.setData(productDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<ResponseStructure<ProductResponseDTO>> deleteProduct(@RequestParam long id) {
        ProductResponseDTO productDTO = productService.deleteProduct(id);

        ResponseStructure<ProductResponseDTO> responseStructure = new ResponseStructure<>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Product deleted successfully!!!");
        responseStructure.setData(productDTO);

        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
}
