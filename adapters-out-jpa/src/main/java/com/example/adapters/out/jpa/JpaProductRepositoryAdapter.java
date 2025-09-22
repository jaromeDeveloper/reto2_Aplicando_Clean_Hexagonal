package com.example.adapters.out.jpa;

import com.example.adapters.out.jpa.entity.ProductEntity;
import com.example.domain.model.Product;
import com.example.domain.port.out.LoadAllProductsPort;
import com.example.domain.port.out.LoadProductPort;
import com.example.domain.port.out.SaveProductPort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
import java.util.stream.Collectors;

interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {}

public class JpaProductRepositoryAdapter implements SaveProductPort, LoadProductPort, LoadAllProductsPort {

    private final ProductJpaRepository repo;

    public JpaProductRepositoryAdapter(ProductJpaRepository repo) { this.repo = repo; }

    @Override
    public Product save(Product product) {
        ProductEntity e = new ProductEntity();
        e.setId(product.getId());
        e.setName(product.getName());
        e.setPrice(product.getPrice());
        repo.save(e);
        return product;
    }

    @Override
    public Optional<Product> loadById(UUID id) {
        return repo.findById(id).map(e -> new Product(e.getId(), e.getName(), e.getPrice()));
    }

    @Override
    public List<Product> loadAll() {
        return repo.findAll().stream()
                .map(e -> new Product(e.getId(), e.getName(), e.getPrice()))
                .collect(Collectors.toList());
    }
}
