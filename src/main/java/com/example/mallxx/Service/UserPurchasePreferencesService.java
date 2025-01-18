package com.example.mallxx.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.example.mallxx.entity.ProductTagAssociation;
import com.example.mallxx.entity.UserPurchaseHistory;
import com.example.mallxx.mapper.ProductTagAssociationMapper;
import com.example.mallxx.mapper.UserPurchaseHistoryMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * 用户购买历史表：10条，标签计数
 */
@Service
public class UserPurchasePreferencesService {

    private final UserPurchaseHistoryMapper userPurchaseHistoryMapper;
    private final ProductTagAssociationMapper productTagAssociationMapper;

    // 构造函数注入Mapper实例
    public UserPurchasePreferencesService(UserPurchaseHistoryMapper userPurchaseHistoryMapper,
                                          ProductTagAssociationMapper productTagAssociationMapper) {
        this.userPurchaseHistoryMapper = userPurchaseHistoryMapper;
        this.productTagAssociationMapper = productTagAssociationMapper;
    }

    /**
     * 根据用户ID获取用户购买历史中的标签偏好
     */
    public Map<Integer, Integer> getUserPurchaseTagPreferences(int userId) {
        int user_id = userId;
        System.out.println("getUserPurchaseTagPreferences"+user_id);

        // 从用户购买历史中获取最近的10个商品
        List<UserPurchaseHistory> list = userPurchaseHistoryMapper.selectByUserId(user_id);
        System.out.println("list"+list);
        List<UserPurchaseHistory> purchaseHistories = userPurchaseHistoryMapper.selectByUserId(user_id)
                .stream()
                .sorted(Comparator.comparing(UserPurchaseHistory::getPurchaseDate).reversed()) // 按时间降序排列
                .limit(10) // 只取最近的10条记录
                .toList();
        System.out.println("purchaseHistories"+purchaseHistories);

        // 创建一个Map来存储标签及其出现的次数
        Map<Integer, Integer> tagCounts = new HashMap<>();

        for (UserPurchaseHistory purchase : purchaseHistories) {
            // 对于每一个商品，获取其所有标签
            List<ProductTagAssociation> tags = productTagAssociationMapper.selectByProductId(purchase.getProductId());
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
