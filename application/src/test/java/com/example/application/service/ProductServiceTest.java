package com.example.application.service;

import com.example.domain.model.Product;
import com.example.domain.port.out.LoadAllProductsPort;
import com.example.domain.port.out.LoadProductPort;
import com.example.domain.port.out.SaveProductPort;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void create_and_get() {
        class Repo implements SaveProductPort, LoadProductPort, LoadAllProductsPort {
            Map<UUID, Product> m = new HashMap<>();
            public Product save(Product p){ m.put(p.getId(), p); return p; }
            public Optional<Product> loadById(UUID id){ return Optional.ofNullable(m.get(id)); }
            public List<Product> loadAll(){ return new ArrayList<>(m.values()); }
        }
        Repo repo = new Repo();
        ProductService s = new ProductService(repo, repo, repo);

        Product created = s.create("Mouse", new BigDecimal("199.99"));
        assertNotNull(created.getId());

        Product loaded = s.getById(created.getId());
        assertEquals("Mouse", loaded.getName());
    }
}
