package com.example.domain.port.out;

import com.example.domain.model.Product;

public interface SaveProductPort {
    Product save(Product product);
}
