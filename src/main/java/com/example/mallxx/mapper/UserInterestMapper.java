package com.example.mallxx.mapper;

import com.example.mallxx.entity.UserInterest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserInterestMapper {

    /**
     * 插入一条新的用户兴趣标签记录。
     * @param userInterest 包含用户兴趣标签信息的对象
     */
    @Insert("INSERT INTO user_interests (user_id, tag_id, interest_score) VALUES (#{userId}, #{tagId}, #{interestScore})")
    @Options(useGeneratedKeys = false)
    void insert(UserInterest userInterest);

    /**
     * 更新指定用户ID和标签ID的用户兴趣标签记录。
     * @param userInterest 包含更新后用户兴趣标签信息的对象
     */
    @Update("UPDATE user_interests SET interest_score = #{interestScore} WHERE user_id = #{userId} AND tag_id = #{tagId}")
    void update(UserInterest userInterest);

    /**
     * 删除指定用户ID和标签ID的用户兴趣标签记录。
     * @param userId 查询条件：用户的ID
     * @param tagId 查询条件：标签的ID
     */
    @Delete("DELETE FROM user_interests WHERE user_id = #{userId} AND tag_id = #{tagId}")
    void deleteByUserIdAndTagId(int userId, int tagId);

    /**
     * 根据用户ID查询该用户的兴趣标签记录。
     * @param userId 查询条件：用户的ID
     * @return 返回该用户所有的兴趣标签列表
     */
    @Select("SELECT * FROM user_interests WHERE user_id = #{userId}")
    List<UserInterest> selectByUserId(int userId);

    /**
     * 根据标签ID查询所有包含该标签的兴趣标签记录。
     * @param tagId 查询条件：标签的ID
     * @return 返回所有包含该标签的兴趣标签列表
     */
    @Select("SELECT * FROM user_interests WHERE tag_id = #{tagId}")
    List<UserInterest> selectByTagId(int tagId);

    /**
     * 根据兴趣得分范围查询用户兴趣标签记录。
     * @param minScore 查询条件：最小兴趣得分（包括）
     * @param maxScore 查询条件：最大兴趣得分（包括）
     * @return 返回在指定兴趣得分范围内的兴趣标签列表
     */
    @Select("SELECT * FROM user_interests WHERE interest_score >= #{minScore} AND interest_score <= #{maxScore}")
    List<UserInterest> selectByInterestScoreRange(@Param("minScore") int minScore, @Param("maxScore") int maxScore);

    /**
     * 查询所有用户兴趣标签记录。
     * @return 返回所有用户的兴趣标签列表
     */
    @Select("SELECT * FROM user_interests")
    List<UserInterest> selectAll();

    /**
     * 根据用户ID和标签ID查询用户兴趣标签记录。
     * @param userId 查询条件：用户的ID
     * @param tagId 查询条件：标签的ID
     * @return 返回指定用户ID和标签ID的兴趣标签记录
     */
    @Select("SELECT * FROM user_interests WHERE user_id = #{userId} AND tag_id = #{tagId}")
    UserInterest selectByUserIdAndTagId(@Param("userId") int userId, @Param("tagId") int tagId);

    /**
     * 检查是否存在指定用户ID和标签ID的兴趣标签记录。
     * @param userId 用户ID
     * @param tagId 标签ID
     * @return 返回记录的数量，如果存在则返回大于0的值，否则返回0
     */
    @Select("SELECT COUNT(*) FROM user_interests WHERE user_id = #{userId} AND tag_id = #{tagId}")
    int existsByUserIdAndTagId(@Param("userId") int userId, @Param("tagId") int tagId);
}
