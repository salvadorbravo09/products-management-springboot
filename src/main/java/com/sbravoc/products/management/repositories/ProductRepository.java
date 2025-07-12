package com.sbravoc.products.management.repositories;

import com.sbravoc.products.management.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
