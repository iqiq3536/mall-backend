package com.example.mallxx.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.example.mallxx.entity.ProductTagAssociation;
import com.example.mallxx.entity.UserBrowsingHistory;
import com.example.mallxx.mapper.ProductTagAssociationMapper;
import com.example.mallxx.mapper.UserBrowsingHistoryMapper;
import org.apache.ibatis.annotations.Param;


/**
*    用户浏览历史表：用户最近浏览的20条商品，根据商品id-->获取标签id,标签计数
*   返回map<tag_id,count>
* */
public class UserPreferencesService {

    private final UserBrowsingHistoryMapper userBrowsingHistoryMapper;
    private final ProductTagAssociationMapper productTagAssociationMapper;

    // 构造函数注入Mapper实例
    public UserPreferencesService(UserBrowsingHistoryMapper userBrowsingHistoryMapper,
                                  ProductTagAssociationMapper productTagAssociationMapper) {
        this.userBrowsingHistoryMapper = userBrowsingHistoryMapper;
        this.productTagAssociationMapper = productTagAssociationMapper;
    }

    /**
     * 根据用户ID获取用户的标签偏好
     */
    public Map<Integer, Integer> getUserTagPreferences(int userId) {
        // 从用户浏览历史中获取最近的20个商品
        List<UserBrowsingHistory> browsingHistories = userBrowsingHistoryMapper.getByUserId(userId)
                .stream()
                .sorted(Comparator.comparing(UserBrowsingHistory::getViewDate).reversed()) // 按时间降序排列
                .limit(10) // 只取最近的20条记录
                .toList();

        // 创建一个Map来存储标签及其出现的次数
        Map<Integer, Integer> tagCounts = new HashMap<>();

        for (UserBrowsingHistory history : browsingHistories) {
            // 对于每一个商品，获取其所有标签
            List<ProductTagAssociation> tags = productTagAssociationMapper.selectByProductId(history.getProduct_id());
            for (ProductTagAssociation tag : tags) {
                // 更新标签计数
                int count = tagCounts.getOrDefault(tag.getTag_id(), 0);
                tagCounts.put(tag.getTag_id(), count + 1);
            }
        }

        // 返回按照出现次数排序后的标签列表
        return tagCounts.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // 遇到重复键时保留第一个值
                        LinkedHashMap::new // 保持插入顺序
                ));
    }
}
