package com.example.mallxx.mapper;

import com.example.mallxx.entity.UserPurchaseHistory;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserPurchaseHistoryMapper {

    /**
     * 插入一条新的用户购买历史记录。
     * @param purchaseHistory 包含用户购买信息的对象
     */
    @Insert("INSERT INTO user_purchase_history (user_id, product_id, quantity, purchase_date) VALUES (#{userId}, #{productId}, #{quantity}, #{purchaseDate})")
    @Options(useGeneratedKeys = true, keyProperty = "purchaseId", keyColumn = "purchase_id")
    void insert(UserPurchaseHistory purchaseHistory);

    /**
     * 根据购买ID删除用户的购买历史记录。
     * @param purchaseId 要删除的购买记录的ID
     */
    @Delete("DELETE FROM user_purchase_history WHERE purchase_id = #{purchaseId}")
    void deleteById(int purchaseId);

    /**
     * 更新指定购买ID的用户购买历史记录。
     * @param purchaseHistory 包含更新后用户购买信息的对象
     */
    @Update("UPDATE user_purchase_history SET user_id = #{userId}, product_id = #{productId}, quantity = #{quantity}, purchase_date = #{purchaseDate} WHERE purchase_id = #{purchaseId}")
    void update(UserPurchaseHistory purchaseHistory);

    /**
     * 根据购买ID查询单条用户购买历史记录。
     * @param purchaseId 查询条件：购买记录的ID
     * @return 返回对应的用户购买历史对象
     */
    @Select("SELECT * FROM user_purchase_history WHERE purchase_id = #{purchaseId}")
    UserPurchaseHistory selectById(int purchaseId);

    /**
     * 根据用户ID查询所有该用户的购买历史记录。
     * @param  ：用户的ID
     * @return 返回该用户所有的购买历史列表
     */
    @Select("SELECT * FROM user_purchase_history WHERE user_id = #{user_id}")
    List<UserPurchaseHistory> selectByUserId(int user_id);

    /**
     * 根据产品ID查询所有包含该产品的购买历史记录。
     * @param productId 查询条件：产品的ID
     * @return 返回所有包含该产品的购买历史列表
     */
    @Select("SELECT * FROM user_purchase_history WHERE product_id = #{productId}")
    List<UserPurchaseHistory> selectByProductId(int productId);

    /**
     * 根据购买日期范围查询用户购买历史记录。
     * @param startDate 查询条件：开始日期（包括）
     * @param endDate 查询条件：结束日期（包括）
     * @return 返回在指定日期范围内的购买历史列表
     */
    @Select("SELECT * FROM user_purchase_history WHERE purchase_date >= #{startDate} AND purchase_date <= #{endDate}")
    List<UserPurchaseHistory> selectByPurchaseDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 查询所有用户购买历史记录。
     * @return 返回所有用户的购买历史列表
     */
    @Select("SELECT * FROM user_purchase_history")
    List<UserPurchaseHistory> selectAll();
}