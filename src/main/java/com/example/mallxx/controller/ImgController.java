package com.example.mallxx.controller;

import com.example.mallxx.entity.Goods;
import com.example.mallxx.mapper.GoodsMapper;
import com.example.mallxx.tools.RandomStringGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("/api")
@RestController
public class ImgController {
    private final GoodsMapper goodsMapper;
    private final String UPLOAD_DIR = "E:\\EXG\\images";

    public ImgController(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @PostMapping("/goods_img/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            //在这里写商品图片上传保存逻辑
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("上传失败: 文件为空");
            }
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            String filename = timestamp + "_" + RandomStringGenerator.generateRandomString(5) + "_" + file.getOriginalFilename();
            File targetFile = new File(UPLOAD_DIR, filename);
            Files.copy(file.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return ResponseEntity.ok(filename);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("上传失败: " + e.getMessage());
        }
    }

    @GetMapping("/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            //在这里写图片调用逻辑
            Path imagePath = Paths.get(UPLOAD_DIR, imageName);
            Resource imageResource = new UrlResource(imagePath.toUri());

            if (imageResource.exists() || imageResource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(imageResource);
            } else {
                throw new FileNotFoundException("Image not found");
            }
        } catch (Exception e) {
            // Handle exception
            return ResponseEntity.notFound().build();
        }
    }


}
