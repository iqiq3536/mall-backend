package com.example.mallxx.controller;

import com.example.mallxx.entity.Product;
import com.example.mallxx.mapper.ProductMapper;
import com.example.mallxx.mapper.Product_merchantMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class MerchantController {
    private final ProductMapper productMapper;
    private final Product_merchantMapper product_merchantMapper;

    public MerchantController(ProductMapper productMapper, Product_merchantMapper Product_merchantMapper) {
        this.product_merchantMapper = Product_merchantMapper;
        this.productMapper = productMapper;
    }

    @RequestMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts(@CookieValue(value = "userId", required = false) String userId) {
        List<Product> products = new ArrayList<>();
        //添加逻辑，审核管理员可获取所有商品
        if (userId != null) {
            products = productMapper.findProductsByMerchantId(Integer.parseInt(userId));
            System.out.println(products.size());
        }
        return ResponseEntity.ok(products);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> editProducts(@CookieValue(value = "userId", required = false) String userId, @PathVariable Long id, @RequestBody Product updatedProduct) {
        Product products = productMapper.findById(id.intValue());
        productMapper.update(id.intValue(), updatedProduct.getName(), updatedProduct.getCategory(), updatedProduct.getImg_url()
                , updatedProduct.getDescription(), updatedProduct.getPrice(), updatedProduct.getStock());
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProducts(@CookieValue(value = "userId", required = false) String userId, @PathVariable Long id) {
        productMapper.deleteById(id.intValue());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProducts(@CookieValue(value = "userId", required = false) String userId, @RequestBody Product product) {
        if(userId != null) {
            productMapper.add2(product);
            System.out.println(product.getId());
            product_merchantMapper.insertProduct_merchant(product.getId(), Integer.parseInt(userId));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping("/er")
    public ResponseEntity<Void> login2(@CookieValue(value = "userId", required = false) String userId) {
        System.out.println(userId);
        return ResponseEntity.ok().build();
    }
}
