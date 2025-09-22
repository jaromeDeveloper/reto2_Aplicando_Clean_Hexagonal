package com.example.application.service;

import com.example.domain.model.Product;
import com.example.domain.port.in.CreateProductUseCase;
import com.example.domain.port.in.GetProductUseCase;
import com.example.domain.port.in.ListProductsUseCase;
import com.example.domain.port.out.LoadAllProductsPort;
import com.example.domain.port.out.LoadProductPort;
import com.example.domain.port.out.SaveProductPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductService implements CreateProductUseCase, GetProductUseCase, ListProductsUseCase {

    private final SaveProductPort savePort;
    private final LoadProductPort loadPort;
    private final LoadAllProductsPort loadAllPort;

    public ProductService(SaveProductPort savePort, LoadProductPort loadPort, LoadAllProductsPort loadAllPort) {
        this.savePort = savePort;
        this.loadPort = loadPort;
        this.loadAllPort = loadAllPort;
    }

    @Override
    public Product create(String name, BigDecimal price) {
        Product p = new Product(null, name, price);
        return savePort.save(p);
    }

    @Override
    public Product getById(UUID id) {
        return loadPort.loadById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Override
    public List<Product> listAll() {
        return loadAllPort.loadAll();
    }
}
