package com.example.domain.port.in;

import com.example.domain.model.Product;
import java.math.BigDecimal;

public interface CreateProductUseCase {
    Product create(String name, BigDecimal price);
}
