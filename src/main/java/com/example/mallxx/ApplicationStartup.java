package com.example.mallxx;

import com.example.mallxx.mapper.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final SellerMapper sellerMapper;

    @Autowired
    public ApplicationStartup(SellerMapper sellerMapper) {
        this.sellerMapper = sellerMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("SellerMapper is initialized: " + (sellerMapper != null));
    }
}
