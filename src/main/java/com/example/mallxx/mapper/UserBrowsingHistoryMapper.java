package com.example.mallxx.mapper;

import com.example.mallxx.entity.UserBrowsingHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserBrowsingHistoryMapper {
    // 1. 根据id获取信息
    @Select("SELECT * FROM user_browsing_history WHERE history_id  = #{history_id}")
    UserBrowsingHistory getById(@Param("history_id") int history_id);
    // 2. 根据userid获取此用户所有历史记录
    @Select("SELECT * FROM user_browsing_history WHERE user_id = #{user_id}")
    List<UserBrowsingHistory> getByUserId(@Param("userId") int userId);
    // 3. 根据userid添加历史记录
    @Insert("INSERT INTO user_browsing_history(user_id, product_id, view_date, seconds) VALUES(#{userId}, #{productId}, #{viewDate}, #{seconds})")
    @Options(useGeneratedKeys = true, keyProperty = "historyId")
    Boolean addHistory(UserBrowsingHistory history);
    // 4. 根据历史ID删除历史记录
    @Delete("DELETE FROM user_browsing_history WHERE history_id = #{history_id}")
    Boolean deleteById(@Param("history_id") int history_id);

}