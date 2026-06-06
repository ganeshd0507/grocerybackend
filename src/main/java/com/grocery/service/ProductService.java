package com.grocery.service;

import com.grocery.entity.Product;
import com.grocery.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        if (product.getId() == null || product.getId().isEmpty()) {
            product.setId(UUID.randomUUID().toString());
        }
        if (product.getImages() == null || product.getImages().isEmpty()) {
            if (product.getImage() != null) {
                product.setImages(new ArrayList<>(List.of(product.getImage())));
            } else {
                product.setImages(new ArrayList<>());
            }
        }
        product.setRating(5.0);
        product.setReviewsCount(0);
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(String id, Product productDetails) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(productDetails.getName());
                    existingProduct.setCategory(productDetails.getCategory());
                    existingProduct.setPrice(productDetails.getPrice());
                    existingProduct.setOldPrice(productDetails.getOldPrice());
                    existingProduct.setDiscount(productDetails.getDiscount());
                    existingProduct.setWeight(productDetails.getWeight());
                    existingProduct.setImage(productDetails.getImage());
                    existingProduct.setInStock(productDetails.isInStock());
                    existingProduct.setEta(productDetails.getEta());
                    existingProduct.setDescription(productDetails.getDescription());
                    if (productDetails.getImages() != null && !productDetails.getImages().isEmpty()) {
                        existingProduct.setImages(new ArrayList<>(productDetails.getImages()));
                    } else if (productDetails.getImage() != null) {
                        existingProduct.setImages(new ArrayList<>(List.of(productDetails.getImage())));
                    } else {
                        existingProduct.setImages(new ArrayList<>());
                    }
                    return productRepository.save(existingProduct);
                });
    }

    public boolean deleteProduct(String id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return true;
                })
                .orElse(false);
    }
}
