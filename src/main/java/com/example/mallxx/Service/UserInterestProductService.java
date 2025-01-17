package com.example.mallxx.Service;

import com.example.mallxx.entity.ProductTagAssociation;
import com.example.mallxx.entity.UserInterest;
import com.example.mallxx.mapper.ProductTagAssociationMapper;
import com.example.mallxx.mapper.UserInterestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class UserInterestProductService {
    @Autowired
    private final UserInterestMapper userInterestMapper;
    @Autowired
    private final ProductTagAssociationMapper productTagAssociationMapper;

    // 构造函数注入其他服务实例
    public UserInterestProductService(UserInterestMapper userInterestMapper,
                                      ProductTagAssociationMapper productTagAssociationMapper) {
        this.userInterestMapper = userInterestMapper;
        this.productTagAssociationMapper = productTagAssociationMapper;
    }

    /**
     * 获取用户最感兴趣的5个标签，并根据这些标签获取推荐商品列表。
     */
    public  List<Integer> getRecommendedProducts(int userId) {
        // 获取用户最感兴趣的5个标签
        List<UserInterest> topInterests = userInterestMapper.selectByUserId(userId)
                .stream()
                .sorted(Comparator.comparingInt(UserInterest::getInterestScore).reversed()) // 按兴趣得分降序排列
                .limit(5) // 只取前5个标签
                .collect(Collectors.toList());

        // 创建一个Map来存储商品及其对应的标签命中次数
        Map<Integer, Integer> productCounts = new HashMap<>();

        // 对于每个标签，查找所有关联的商品，并更新商品的标签命中次数
        for (UserInterest interest : topInterests) {
            List<ProductTagAssociation> productsWithTag = productTagAssociationMapper.selectByTagId(interest.getTagId());
            for (ProductTagAssociation pta : productsWithTag) {
                int count = productCounts.getOrDefault(pta.getProductId(), 0);
                productCounts.put(pta.getProductId(), count + 1);
            }
        }

        // 根据商品命中的标签数量进行降序排序
        List<Integer> recommendedProductIds = productCounts.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return recommendedProductIds;
    }
}

