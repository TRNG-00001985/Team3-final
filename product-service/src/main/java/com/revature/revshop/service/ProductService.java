package com.revature.revshop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.revshop.dto.CategoryDto;
import com.revature.revshop.dto.ProductRequest;
import com.revature.revshop.dto.ProductResponse;
import com.revature.revshop.model.Category;
import com.revature.revshop.model.Product;
import com.revature.revshop.repository.CategoryRepository;
import com.revature.revshop.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public boolean existsBySkuCode(String skuCode) {
        return productRepository.existsBySkuCode(skuCode);
    }

    // Mapping ProductRequest to Product entity
    public Product mapToProduct(ProductRequest productRequest, String userId) {
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .skuCode(productRequest.getSkuCode())
                .price(productRequest.getPrice())
                .imageurl(productRequest.getImageurl())
                .category(category)
                .userId(userId) // Set userId from the request
                .build();
    }

    // Mapping Product entity to ProductResponse DTO
    public ProductResponse mapToProductResponse(Product product) {
        Category category = product.getCategory();
        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

        return ProductResponse.builder()
                .id(product.getId())
                .userId(product.getUserId()) // Add userId here
                .name(product.getName())
                .description(product.getDescription())
                .skuCode(product.getSkuCode())
                .price(product.getPrice())
                .imageurl(product.getImageurl())
                .categoryId(category.getId()) // Change to categoryId
                .build();
    }

    // Create Product
    public ProductResponse createProduct(ProductRequest productRequest, String userId) { // Accept userId
        Product product = mapToProduct(productRequest, userId);
        product = productRepository.save(product);
        return mapToProductResponse(product);
    }

    // Get Product by Id
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToProductResponse(product);
    }

    // Update Product by Id
    public ProductResponse updateProductById(ProductRequest productRequest, Long id, String userId) { // Accept userId
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setSkuCode(productRequest.getSkuCode());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setImageurl(productRequest.getImageurl());
        existingProduct.setUserId(userId); // Update userId if needed

        if (productRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category);
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToProductResponse(updatedProduct);
    }

    // Delete Product by Id
    public boolean deleteProductById(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all products
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    public List<ProductResponse> searchProducts(String query) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(query);
        return products.stream()
                       .map(this::mapToProductResponse)
                       .collect(Collectors.toList());
    }

    public List<Product> getAllProductsAsEntities() {
        return productRepository.findAll(); // Assuming you have a repository method to fetch all products
    }

    public List<ProductResponse> searchProducts1(String query) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(query);
        return products.stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }
    
    public List<ProductResponse> getProductsByUserId(String userId) {
        List<Product> products = productRepository.findByUserId(userId);
        return products.stream()
                       .map(this::mapToProductResponse)
                       .collect(Collectors.toList());
    }

}
