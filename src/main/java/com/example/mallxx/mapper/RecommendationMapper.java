package com.example.mallxx.mapper;

import com.example.mallxx.entity.Recommendation;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RecommendationMapper {

    /**
     * 插入一条新的推荐记录。
     * @param recommendation 包含推荐信息的对象
     */
    @Insert("INSERT INTO recommendations (user_id, product_id, recommended_at, action_taken) VALUES (#{userId}, #{productId}, #{recommendedAt}, #{actionTaken})")
    @Options(useGeneratedKeys = true, keyProperty = "recommendationId", keyColumn = "recommendation_id")
    void insert(Recommendation recommendation);

    /**
     * 根据推荐记录ID删除推荐记录。
     * @param recommendationId 要删除的推荐记录的ID
     */
    @Delete("DELETE FROM recommendations WHERE recommendation_id = #{recommendationId}")
    void deleteById(int recommendationId);

    /**
     * 更新指定推荐记录ID的推荐记录。
     * @param recommendation 包含更新后推荐信息的对象
     */
    @Update("UPDATE recommendations SET user_id = #{userId}, product_id = #{productId}, recommended_at = #{recommendedAt}, action_taken = #{actionTaken} WHERE recommendation_id = #{recommendationId}")
    void update(Recommendation recommendation);

    /**
     * 根据推荐记录ID查询单条推荐记录。
     * @param recommendationId 查询条件：推荐记录的ID
     * @return 返回对应的推荐对象
     */
    @Select("SELECT * FROM recommendations WHERE recommendation_id = #{recommendationId}")
    Recommendation selectById(int recommendationId);

    /**
     * 根据用户ID查询该用户的推荐记录。
     * @param userId 查询条件：用户的ID
     * @return 返回该用户所有的推荐记录列表
     */
    @Select("SELECT * FROM recommendations WHERE user_id = #{userId}")
    List<Recommendation> selectByUserId(int userId);

    /**
     * 根据产品ID查询所有包含该产品的推荐记录。
     * @param productId 查询条件：产品的ID
     * @return 返回所有包含该产品的推荐记录列表
     */
    @Select("SELECT * FROM recommendations WHERE product_id = #{productId}")
    List<Recommendation> selectByProductId(int productId);

    /**
     * 根据推荐时间范围查询推荐记录。
     * @param startDate 查询条件：开始日期（包括）
     * @param endDate 查询条件：结束日期（包括）
     * @return 返回在指定日期范围内的推荐记录列表
     */
    @Select("SELECT * FROM recommendations WHERE recommended_at >= #{startDate} AND recommended_at <= #{endDate}")
    List<Recommendation> selectByRecommendedDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 根据用户行为类型查询推荐记录。
     * @param actionTaken 查询条件：用户行为类型 ('view', 'purchase')
     * @return 返回指定行为类型的推荐记录列表
     */
    @Select("SELECT * FROM recommendations WHERE action_taken = #{actionTaken}")
    List<Recommendation> selectByActionTaken(String actionTaken);

    /**
     * 查询所有推荐记录。
     * @return 返回所有推荐记录列表
     */
    @Select("SELECT * FROM recommendations")
    List<Recommendation> selectAll();
}