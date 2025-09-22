package com.example.adapters.in.rest;

import com.example.domain.model.Product;
import com.example.domain.port.in.CreateProductUseCase;
import com.example.domain.port.in.GetProductUseCase;
import com.example.domain.port.in.ListProductsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createUC;
    private final GetProductUseCase getUC;
    private final ListProductsUseCase listUC;

    public ProductController(CreateProductUseCase createUC, GetProductUseCase getUC, ListProductsUseCase listUC) {
        this.createUC = createUC;
        this.getUC = getUC;
        this.listUC = listUC;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Map<String, Object> body) {
        String name = (String) body.get("name");
        BigDecimal price = new BigDecimal(body.get("price").toString());
        return ResponseEntity.ok(createUC.create(name, price));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable UUID id) {
        return ResponseEntity.ok(getUC.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(listUC.listAll());
    }
}
