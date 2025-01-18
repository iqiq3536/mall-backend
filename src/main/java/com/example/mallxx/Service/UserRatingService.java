package com.example.mallxx.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.example.mallxx.entity.ProductTagAssociation;
import com.example.mallxx.entity.UserRating;
import com.example.mallxx.mapper.ProductTagAssociationMapper;
import com.example.mallxx.mapper.UserRatingMapper;
import org.apache.ibatis.annotations.Param;

public class UserRatingService {

    private final UserRatingMapper userRatingMapper;
    private final ProductTagAssociationMapper productTagAssociationMapper;

    // 构造函数注入Mapper实例
    public UserRatingService(UserRatingMapper userRatingMapper,
                             ProductTagAssociationMapper productTagAssociationMapper) {
        this.userRatingMapper = userRatingMapper;
        this.productTagAssociationMapper = productTagAssociationMapper;
    }

    /**
     * 根据用户ID获取用户对标签的评分（取平均值）
     */
    public Map<Integer, Double> getUserTagRatings(int userId) {
        // 获取用户所有的评分记录
        List<UserRating> ratings = userRatingMapper.selectByUserId(userId);

        // 创建一个Map来存储标签及其累积评分和计数
        Map<Integer, TagRatingAccumulator> tagRatings = new HashMap<>();

        for (UserRating rating : ratings) {
            // 对于每一个商品，获取其所有标签
            List<ProductTagAssociation> tags = productTagAssociationMapper.selectByProductId(rating.getProductId());
            for (ProductTagAssociation tag : tags) {
                // 更新标签评分累积器
                TagRatingAccumulator accumulator = tagRatings.computeIfAbsent(tag.getTag_id(), k -> new TagRatingAccumulator());
                accumulator.addRating(rating.getRating());
            }
        }

        // 计算每个标签的平均评分
        Map<Integer, Double> averageTagRatings = tagRatings.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getAverageRating()
                ));

        return averageTagRatings;
    }

    // 辅助类用于累积标签评分
    private static class TagRatingAccumulator {
        private double totalRating = 0;
        private int count = 0;

        public void addRating(int rating) {
            totalRating += rating;
            count++;
        }

        public double getAverageRating() {
            return count == 0 ? 0 : totalRating / count;
        }
    }
}