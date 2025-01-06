package com.example.mallxx.controller;

import com.example.mallxx.mapper.GoodsMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
public class GoodsController {
    private final GoodsMapper goodsMapper;

    public GoodsController(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @RequestMapping("/goods")
    public String goods() {
        return "GOODS";
    }


}
