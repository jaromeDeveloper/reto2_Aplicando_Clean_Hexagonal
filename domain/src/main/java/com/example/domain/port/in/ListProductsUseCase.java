package com.example.domain.port.in;

import com.example.domain.model.Product;
import java.util.List;

public interface ListProductsUseCase {
    List<Product> listAll();
}
