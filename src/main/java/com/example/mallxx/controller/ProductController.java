package com.example.mallxx.controller;

import com.example.mallxx.entity.Product;
import com.example.mallxx.mapper.ProductMapper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductMapper productMapper;

    public ProductController(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productMapper.findAll();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> editProducts(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product products = productMapper.findById(id.intValue());
        productMapper.update(id.intValue(), updatedProduct.getName(), updatedProduct.getImg_url());
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProducts(@PathVariable Long id) {
        productMapper.deleteById(id.intValue());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProducts(@RequestBody Product product) {
        System.out.println(product.getName());
        System.out.println(product.getImg_url());
        productMapper.add(product.getName(), product.getImg_url());
        return ResponseEntity.noContent().build();
    }








}
