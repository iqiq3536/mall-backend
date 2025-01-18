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
    private final UserPurchasePreferencesService UserPurchasePreferencesService;

    // 构造函数注入其他服务实例
    public UserInterestProductService(UserInterestMapper userInterestMapper,
                                      ProductTagAssociationMapper productTagAssociationMapper,UserPurchasePreferencesService UserPurchasePreferencesService) {
        this.userInterestMapper = userInterestMapper;
        this.productTagAssociationMapper = productTagAssociationMapper;
        this.UserPurchasePreferencesService = UserPurchasePreferencesService;
    }


    /**
     * 获取用户最感兴趣的5个标签，并根据这些标签获取推荐商品列表。
     */
    public  List<Integer> getRecommendedProducts(int user_id) {
        //UserPurchasePreferencesService.getUserPurchaseTagPreferences(user_id);
        // 获取用户最感兴趣的5个标签
        System.out.println(user_id+"---/-/-0zhiqian/-/-/-/-/-/-//-/-/--/-\n");
        if (user_id==1){
            user_id =3536;
            System.out.println("userId=3536");
        }
        System.out.println(user_id+"---/-/-/-0zhihou/-/-/-/-/-//-/-/--/-\n");
        List<UserInterest> List = userInterestMapper.selectByUserId(user_id);
        System.out.println(List+"usst----------------\n");
        List<UserInterest> topInterests = userInterestMapper.selectByUserId(user_id)
                .stream()
                .sorted(Comparator.comparingInt(UserInterest::getInterest_score).reversed()) // 按兴趣得分降序排列
                .limit(5) // 只取前5个标签
                .collect(Collectors.toList());
        System.out.println("topInterests----------------\n");
        System.out.println(topInterests);

        // 创建一个Map来存储商品及其对应的标签命中次数
        Map<Integer, Integer> productCounts = new HashMap<>();

        // 对于每个标签，查找所有关联的商品，并更新商品的标签命中次数
        for (UserInterest interest : topInterests) {
            List<ProductTagAssociation> productsWithTag = productTagAssociationMapper.selectByTagId(interest.getTag_id());
            for (ProductTagAssociation pta : productsWithTag) {
                int count = productCounts.getOrDefault(pta.getProduct_id(), 0);
                productCounts.put(pta.getProduct_id(), count + 1);
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

