package com.example.domain.port.out;

import com.example.domain.model.Product;
import java.util.List;

public interface LoadAllProductsPort {
    List<Product> loadAll();
}
