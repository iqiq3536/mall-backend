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
    @Insert("INSERT INTO user_interests (user_id, tag_id, interest_score) VALUES (#{user_id}, #{tag_id}, #{interest_score})")
    @Options(useGeneratedKeys = false)
    void insert(UserInterest userInterest);

    /**
     * 更新指定用户ID和标签ID的用户兴趣标签记录。
     * @param userInterest 包含更新后用户兴趣标签信息的对象
     */
    @Update("UPDATE user_interests SET interest_score = #{interestScore} WHERE user_id = #{user_id} AND tag_id = #{tag_id}")
    void update(UserInterest userInterest);

    /**
     * 删除指定用户ID和标签ID的用户兴趣标签记录。
     * @param  ：用户的ID
     * @param  ：标签的ID
     */
    @Delete("DELETE FROM user_interests WHERE user_id = #{user_id} AND tag_id = #{tag_id}")
    void deleteByUserIdAndTagId(int user_id, int tag_id);

    /**
     * 根据用户ID查询该用户的兴趣标签记录。
     * @param  ：用户的ID
     * @return 返回该用户所有的兴趣标签列表
     * List<UserInterest> List = userInterestMapper.selectByUserId(userId);
     */

    @Select("SELECT * FROM user_interests WHERE user_id = #{user_id}")
    List<UserInterest> selectByUserId(@Param("user_id") int user_id);

    /**
     * 根据标签ID查询所有包含该标签的兴趣标签记录。
     * @param  ：标签的ID
     * @return 返回所有包含该标签的兴趣标签列表
     */
    @Select("SELECT * FROM user_interests WHERE tag_id = #{tag_id}")
    List<UserInterest> selectByTagId(int tag_id);

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
     * @param  ：用户的ID
     * @param  ：标签的ID
     * @return 返回指定用户ID和标签ID的兴趣标签记录
     */
    @Select("SELECT * FROM user_interests WHERE user_id = #{user_id} AND tag_id = #{tag_id}")
    UserInterest selectByUserIdAndTagId(@Param("user_id") int user_id, @Param("tag_id") int tag_id);

    /**
     * 检查是否存在指定用户ID和标签ID的兴趣标签记录。
     * @param
     * @param
     * @return 返回记录的数量，如果存在则返回大于0的值，否则返回0
     */
    @Select("SELECT COUNT(*) FROM user_interests WHERE user_id = #{user_id} AND tag_id = #{tag_Id}")
    int existsByUserIdAndTagId(@Param("user_id") int user_id, @Param("tag_id") int tag_Id);
}
