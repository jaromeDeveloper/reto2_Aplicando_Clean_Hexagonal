package com.example.adapters.out.inmemory;

import com.example.domain.model.Product;
import com.example.domain.port.out.LoadAllProductsPort;
import com.example.domain.port.out.LoadProductPort;
import com.example.domain.port.out.SaveProductPort;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryProductRepository implements SaveProductPort, LoadProductPort, LoadAllProductsPort {

    private final Map<UUID, Product> store = new ConcurrentHashMap<>();

    @Override
    public Product save(Product product) {
        store.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> loadById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Product> loadAll() {
        return new ArrayList<>(store.values());
    }
}
