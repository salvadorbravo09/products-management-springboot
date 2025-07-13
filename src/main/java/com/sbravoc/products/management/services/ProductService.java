package com.sbravoc.products.management.services;

import com.sbravoc.products.management.dtos.ProductCreateDTO;
import com.sbravoc.products.management.dtos.ProductResponseDTO;
import com.sbravoc.products.management.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(Long id);

    ProductResponseDTO createProduct(ProductCreateDTO productCreateDTO);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
