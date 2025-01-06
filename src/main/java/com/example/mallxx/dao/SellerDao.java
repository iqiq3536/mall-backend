package com.example.mallxx.dao;
import com.example.mallxx.entity.Seller;
import com.example.mallxx.mapper.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerDao {
    private final SellerMapper sellerMapper;

    @Autowired
    public SellerDao(SellerMapper sellerMapper) {
        this.sellerMapper = sellerMapper;
    }

    List<Seller> findAll() {
        return sellerMapper.findAll();
    }

}
