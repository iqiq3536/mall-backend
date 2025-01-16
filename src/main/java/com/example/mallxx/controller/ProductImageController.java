package com.example.mallxx.controller;


import com.example.mallxx.entity.ProductDetail;
import com.example.mallxx.mapper.ProductDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/api/product_images")
public class ProductImageController {

    private final String basePath = "E:/image"; // 图片基础路径

    @Autowired
    private ProductDetailMapper productDetailMapper;

    // 获取图片详情列表
    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductDetail>> getProductImages(@PathVariable Long productId) {
        try {
            // 从数据库获取图片详情
            List<ProductDetail> productDetails = productDetailMapper.getProductDetailsByProductId(productId);

            if (productDetails.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // 返回图片信息
            return ResponseEntity.ok(productDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // 根据文件名返回图片资源
    @GetMapping("/file/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        try {
            // 构建图片的完整路径
            Path imagePath = Paths.get(basePath).resolve(fileName).normalize();
            Resource resource = new UrlResource(imagePath.toUri());

            // 检查文件是否存在和可读
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build(); // 如果文件不存在
            }

            // 确定文件的 MIME 类型
            String contentType = Files.probeContentType(imagePath);
            if (contentType == null) {
                contentType = "application/octet-stream"; // 默认类型
            }

            // 返回图片内容
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, contentType) // 设置正确的 MIME 类型
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}







