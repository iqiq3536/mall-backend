package com.example.mallxx.Service;

import java.util.*;
import java.util.stream.Collectors;

public class UserTagPreferenceAggregator {

    private final UserPreferencesService userPreferencesService;
    private final UserPurchasePreferencesService userPurchasePreferencesService;
    private final UserRatingService userRatingService;

    // 构造函数注入其他服务实例
    public UserTagPreferenceAggregator(UserPreferencesService userPreferencesService,
                                       UserPurchasePreferencesService userPurchasePreferencesService,
                                       UserRatingService userRatingService) {
        this.userPreferencesService = userPreferencesService;
        this.userPurchasePreferencesService = userPurchasePreferencesService;
        this.userRatingService = userRatingService;
    }

    /**
     * 根据用户ID获取用户的综合标签偏好分数
     */
    public Map<Integer, Double> getUserCompositeTagPreferences(int userId) {
        // 获取三个服务的数据
        Map<Integer, Integer> preferencesCounts = userPreferencesService.getUserTagPreferences(userId);
        Map<Integer, Integer> purchaseCounts = userPurchasePreferencesService.getUserPurchaseTagPreferences(userId);
        Map<Integer, Double> ratingAverages = userRatingService.getUserTagRatings(userId);

        // 创建一个Map来存储标签及其综合分数
        Map<Integer, Double> compositeScores = new HashMap<>();

        // 定义权重
        final double BROWSING_WEIGHT = 2.0;
        final double PURCHASE_WEIGHT = 4.0;
        final double RATING_WEIGHT = 100.0;

        // 整合三个服务的数据
        Set<Integer> allTags = new HashSet<>(preferencesCounts.keySet());
        allTags.addAll(purchaseCounts.keySet());
        allTags.addAll(ratingAverages.keySet());

        for (Integer tagId : allTags) {
            double score = 0;

            // 加入浏览历史的分数
            Integer browsingCount = preferencesCounts.get(tagId);
            if (browsingCount != null) {
                score += BROWSING_WEIGHT * browsingCount;
            }

            // 加入购买历史的分数
            Integer purchaseCount = purchaseCounts.get(tagId);
            if (purchaseCount != null) {
                score += PURCHASE_WEIGHT * purchaseCount;
            }

            // 加入评分的分数
            Double ratingAverage = ratingAverages.get(tagId);
            if (ratingAverage != null) {
                score += RATING_WEIGHT * ratingAverage;
            }

            compositeScores.put(tagId, score);
        }

        // 按照综合分数降序排序
        return compositeScores.entrySet().stream()
                .sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // 遇到重复键时保留第一个值
                        LinkedHashMap::new // 保持插入顺序
                ));
    }
}