package com.example.mallxx.controller;

import com.example.mallxx.mapper.ProductExamMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/exam_product")
public class ProductExamController {
    private final ProductExamMapper productExamMapper;

    ProductExamController(ProductExamMapper productExamMapper) {
        this.productExamMapper = productExamMapper;
    }

    @RequestMapping("/test")
    public ResponseEntity<Integer> testProduct(int id) {
        System.out.println(id);
        return ResponseEntity.ok(productExamMapper.testProduct(id));
    }

    @RequestMapping("/down")
    public ResponseEntity<Void> DownProduct(@RequestBody Map<String, Integer> data) {
        int id = data.get("id");
        System.out.println(id);
        productExamMapper.DownProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping ("/up")
    public ResponseEntity<Void> UpProduct(@RequestBody Map<String, Integer> data) {
        int id = data.get("id");
        System.out.println(id);
        productExamMapper.UpProduct(id);
        return ResponseEntity.ok().build();
    }
}
