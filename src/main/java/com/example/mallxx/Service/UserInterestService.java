package com.example.mallxx.Service;

import com.example.mallxx.entity.UserInterest;
import com.example.mallxx.mapper.UserInterestMapper;

import java.util.*;
import java.util.stream.Collectors;

public class UserInterestService {

    private final UserTagPreferenceAggregator userTagPreferenceAggregator;
    private final UserInterestMapper userInterestMapper;

    // 构造函数注入其他服务实例
    public UserInterestService(UserTagPreferenceAggregator userTagPreferenceAggregator,
                               UserInterestMapper userInterestMapper) {
        this.userTagPreferenceAggregator = userTagPreferenceAggregator;
        this.userInterestMapper = userInterestMapper;
    }

    /**
     * 更新或插入用户的兴趣标签记录
     */
    public void updateUserInterests(int userId) {
        // 获取用户的综合标签偏好分数
        Map<Integer, Double> compositeScores = userTagPreferenceAggregator.getUserCompositeTagPreferences(userId);

        // 将分数转换为整数，因为数据库中interest_score字段是INT类型
        Map<Integer, Integer> intCompositeScores = compositeScores.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (int) Math.round(entry.getValue())
                ));

        // 遍历所有标签，更新或插入兴趣标签记录
        for (Map.Entry<Integer, Integer> entry : intCompositeScores.entrySet()) {
            UserInterest userInterest = new UserInterest();
            userInterest.setUserId(userId);
            userInterest.setTagId(entry.getKey());
            userInterest.setInterestScore(entry.getValue());

            // 检查是否存在该用户的标签记录
            if (existsUserInterestRecord(userId, entry.getKey())) {
                // 更新现有的记录
                userInterestMapper.update(userInterest);
            } else {
                // 插入新记录
                userInterestMapper.insert(userInterest);
            }
        }
    }

    /**
     * 检查是否存在指定用户ID和标签ID的兴趣标签记录
     */
    private boolean existsUserInterestRecord(int userId, int tagId) {
        return userInterestMapper.existsByUserIdAndTagId(userId, tagId) > 0;
    }
}
