package com.sbravoc.products.management.services.impl;

import com.sbravoc.products.management.dtos.ProductCreateDTO;
import com.sbravoc.products.management.dtos.ProductResponseDTO;
import com.sbravoc.products.management.entities.Product;
import com.sbravoc.products.management.exceptions.InvalidProductDataException;
import com.sbravoc.products.management.exceptions.ProductAlreadyExistsException;
import com.sbravoc.products.management.exceptions.ProductNotFoundException;
import com.sbravoc.products.management.mapper.ProductMapper;
import com.sbravoc.products.management.repositories.ProductRepository;
import com.sbravoc.products.management.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toResponseDTO).toList();
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return productMapper.toResponseDTO(product);
    }

    @Override
    public ProductResponseDTO createProduct(ProductCreateDTO productCreateDTO) {
        List<Product> existingProducts = productRepository.findByName(productCreateDTO.getName());
        if (!existingProducts.isEmpty()) {
            throw new ProductAlreadyExistsException("El producto ya existe con el nombre: " + productCreateDTO.getName());
        }
        validateProductData(productCreateDTO);
        Product product = productMapper.toEntity(productCreateDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponseDTO(savedProduct);
    }


    @Override
    public Product updateProduct(Long id, Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setDescription(product.getDescription());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void validateProductData(ProductCreateDTO productCreateDTO) {
        if (productCreateDTO.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidProductDataException("El precio debe ser mayor que cero");
        }
    }
}
