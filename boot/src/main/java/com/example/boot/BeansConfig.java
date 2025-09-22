package com.example.boot;

import com.example.application.service.ProductService;
import com.example.domain.port.in.CreateProductUseCase;
import com.example.domain.port.in.GetProductUseCase;
import com.example.domain.port.in.ListProductsUseCase;
import com.example.adapters.out.inmemory.InMemoryProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public InMemoryProductRepository inMemoryRepo() {
        return new InMemoryProductRepository();
    }

    @Bean
    public ProductService productService(InMemoryProductRepository repo) {
        return new ProductService(repo, repo, repo);
    }

    @Bean public CreateProductUseCase createUC(ProductService s) { return s; }
    @Bean public GetProductUseCase getUC(ProductService s) { return s; }
    @Bean public ListProductsUseCase listUC(ProductService s) { return s; }
}
