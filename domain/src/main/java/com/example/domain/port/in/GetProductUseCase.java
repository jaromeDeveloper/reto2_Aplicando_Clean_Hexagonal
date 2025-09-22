package com.example.domain.port.in;

import com.example.domain.model.Product;
import java.util.UUID;

public interface GetProductUseCase {
    Product getById(UUID id);
}
