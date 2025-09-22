package com.example.domain.port.out;

import com.example.domain.model.Product;
import java.util.Optional;
import java.util.UUID;

public interface LoadProductPort {
    Optional<Product> loadById(UUID id);
}
