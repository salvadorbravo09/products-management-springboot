package com.sbravoc.products.management.mapper;

import com.sbravoc.products.management.dtos.ProductCreateDTO;
import com.sbravoc.products.management.dtos.ProductResponseDTO;
import com.sbravoc.products.management.dtos.ProductUpdateDTO;
import com.sbravoc.products.management.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(ProductCreateDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        return product;
    }

    public ProductResponseDTO toResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        return dto;
    }

    public void updateEntity(Product product, ProductUpdateDTO dto) {
        if (dto.getName() != null) {
            product.setName(dto.getName());
        }

        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }

        if (dto.getDescription() != null) {
            product.setDescription(dto.getDescription());
        }
    }
}
