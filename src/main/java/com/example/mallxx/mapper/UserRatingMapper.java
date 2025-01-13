package com.example.mallxx.mapper;

import com.example.mallxx.entity.UserRating;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserRatingMapper {

    /**
     * 插入一条新的用户评分记录。
     * @param userRating 包含用户评分信息的对象
     */
    @Insert("INSERT INTO user_ratings (user_id, product_id, rating, review, rating_date) VALUES (#{userId}, #{productId}, #{rating}, #{review}, #{ratingDate})")
    @Options(useGeneratedKeys = true, keyProperty = "ratingId", keyColumn = "rating_id")
    void insert(UserRating userRating);

    /**
     * 根据评分ID删除用户的评分记录。
     * @param ratingId 要删除的评分记录的ID
     */
    @Delete("DELETE FROM user_ratings WHERE rating_id = #{ratingId}")
    void deleteById(int ratingId);

    /**
     * 更新指定评分ID的用户评分记录。
     * @param userRating 包含更新后用户评分信息的对象
     */
    @Update("UPDATE user_ratings SET user_id = #{userId}, product_id = #{productId}, rating = #{rating}, review = #{review}, rating_date = #{ratingDate} WHERE rating_id = #{ratingId}")
    void update(UserRating userRating);

    /**
     * 根据评分ID查询单条用户评分记录。
     * @param ratingId 查询条件：评分记录的ID
     * @return 返回对应的用户评分对象
     */
    @Select("SELECT * FROM user_ratings WHERE rating_id = #{ratingId}")
    UserRating selectById(int ratingId);

    /**
     * 根据用户ID查询所有该用户的评分记录。
     * @param userId 查询条件：用户的ID
     * @return 返回该用户所有的评分记录列表
     */
    @Select("SELECT * FROM user_ratings WHERE user_id = #{userId}")
    List<UserRating> selectByUserId(int userId);

    /**
     * 根据产品ID查询所有包含该产品的评分记录。
     * @param productId 查询条件：产品的ID
     * @return 返回所有包含该产品的评分记录列表
     */
    @Select("SELECT * FROM user_ratings WHERE product_id = #{productId}")
    List<UserRating> selectByProductId(int productId);

    /**
     * 根据评分范围查询用户评分记录。
     * @param minRating 查询条件：最小评分（包括）
     * @param maxRating 查询条件：最大评分（包括）
     * @return 返回在指定评分范围内的评分记录列表
     */
    @Select("SELECT * FROM user_ratings WHERE rating >= #{minRating} AND rating <= #{maxRating}")
    List<UserRating> selectByRatingRange(@Param("minRating") int minRating, @Param("maxRating") int maxRating);

    /**
     * 根据评分日期范围查询用户评分记录。
     * @param startDate 查询条件：开始日期（包括）
     * @param endDate 查询条件：结束日期（包括）
     * @return 返回在指定日期范围内的评分记录列表
     */
    @Select("SELECT * FROM user_ratings WHERE rating_date >= #{startDate} AND rating_date <= #{endDate}")
    List<UserRating> selectByRatingDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 查询所有用户评分记录。
     * @return 返回所有用户的评分记录列表
     */
    @Select("SELECT * FROM user_ratings")
    List<UserRating> selectAll();
}