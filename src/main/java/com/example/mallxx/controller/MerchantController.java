package com.example.mallxx.controller;

import com.example.mallxx.entity.Product;
import com.example.mallxx.entity.ProductTag;
import com.example.mallxx.mapper.ProductMapper;
import com.example.mallxx.mapper.ProductTagMapper;
import com.example.mallxx.mapper.Product_merchantMapper;
import com.huaban.analysis.jieba.JiebaSegmenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class MerchantController {
    private final ProductMapper productMapper;
    private final ProductTagMapper productTagMapper;
    private final Product_merchantMapper product_merchantMapper;

    public MerchantController(ProductMapper productMapper, Product_merchantMapper Product_merchantMapper, ProductTagMapper productTagMapper) {
        this.product_merchantMapper = Product_merchantMapper;
        this.productMapper = productMapper;
        this.productTagMapper = productTagMapper;
    }

    @RequestMapping("/tags/add/{id}")
    public ResponseEntity<ProductTag> addProductTag3(@CookieValue(value = "userId", required = false) String userId, @PathVariable Long id, @RequestBody ProductTag productTag) {
        ProductTag productTag1 = productTagMapper.isExist(productTag.getTag_name());
        if(productTag1 == null) {
            productTagMapper.add(productTag);
            productTag1 = productTag;
        }
        if(productTagMapper.isExistByProductIdANDTagId(id.intValue(), productTag1.getId()) != null) {
            return ResponseEntity.ok().build();
        }
        productTagMapper.insertByProductIdANDTagId(id.intValue(), productTag1.getId());
        return ResponseEntity.ok(productTag);
    }

    @DeleteMapping("/tags/delete/{productId}/{tagId}")
    public ResponseEntity<Void> deleteProductTag(@CookieValue(value = "userId", required = false) String userId, @PathVariable Long productId, @PathVariable Long tagId) {
        productTagMapper.deleteByProductIdANDTagId(productId.intValue(), tagId.intValue());
        return ResponseEntity.noContent().build();
    }

    @RequestMapping("/tags/{id}")
    public ResponseEntity<List<ProductTag>> getProductTag(@CookieValue(value = "userId", required = false) String userId, @PathVariable Long id) {
        List<ProductTag> productTags;
        productTags = productTagMapper.selectById2(id.intValue());
        System.out.println(productTags);
        return ResponseEntity.ok(productTags);
    }

    @RequestMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts(@CookieValue(value = "merchantId", required = false) String merchantId) {
        List<Product> products = new ArrayList<>();
        System.out.println(merchantId);
        if (merchantId != null) {
            products = productMapper.findProductsByMerchantId(Integer.parseInt(merchantId));
            System.out.println(products.size());
        }
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/list_admin")
    public ResponseEntity<List<Product>> admin_getAllProducts(@CookieValue(value = "admin_id", required = false) String adminId) {
        List<Product> products = new ArrayList<>();
        if (adminId != null) {
            products = productMapper.findAll();
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
    public ResponseEntity<Void> addProducts(@CookieValue(value = "merchantId", required = false) String merchantId, @RequestBody Product product) {
        if(merchantId != null) {
            productMapper.add2(product);
            System.out.println(product.getDescription());
            JiebaSegmenter segmenter = new JiebaSegmenter();
            List list = segmenter.sentenceProcess(product.getDescription());
            for(Object str : list) {
                String tag = str.toString();
                ProductTag productTag = new ProductTag();
                productTag.setTag_name(tag);
                ProductTag productTag1 = productTagMapper.isExist(productTag.getTag_name());
                if(productTag1 == null) {
                    productTagMapper.add(productTag);
                    productTag1 = productTag;
                }
                if(productTagMapper.isExistByProductIdANDTagId(product.getId(), productTag1.getId()) == null) {
                    //未存在联系的逻辑
                    productTagMapper.insertByProductIdANDTagId(product.getId(), productTag1.getId());
                }

            }
            System.out.println(product.getId());
            product_merchantMapper.insertProduct_merchant(product.getId(), Integer.parseInt(merchantId));
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
