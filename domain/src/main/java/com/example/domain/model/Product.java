package com.example.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final String name;
    private final BigDecimal price;

    public Product(UUID id, String name, BigDecimal price) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name is required");
        if (price == null || price.signum() < 0) throw new IllegalArgumentException("price must be >= 0");
        this.id = id == null ? UUID.randomUUID() : id;
        this.name = name.trim();
        this.price = price;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
}
