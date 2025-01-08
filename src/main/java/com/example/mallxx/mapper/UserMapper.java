package com.example.mallxx.mapper;

import com.example.mallxx.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    // 查询所有用户
    @Select("SELECT * FROM users")
    List<User> findAll();

    // 根据ID查询用户
    @Select("SELECT * FROM users WHERE user_id = #{userId}")
    User findById(@Param("userId") int userId);

    // 插入新用户
    @Insert("INSERT INTO user(user_id, username, password, full_name, gender, contact_info, family_id, family_name) " +
            "VALUES(#{user.user_id}, #{user.username}, #{user.password}, #{user.full_name}, #{user.gender}, #{user.contact_info}, #{user.family_id}, #{user.family_name})")
    void insert(@Param("user") User user);

    // 更新用户信息
    @Update("UPDATE user SET username = #{user.username}, password = #{user.password}, full_name = #{user.full_name}, " +
            "gender = #{user.gender}, contact_info = #{user.contact_info}, family_id = #{user.family_id}, family_name = #{user.family_name} " +
            "WHERE user_id = #{user.user_id}")
    void update(@Param("user") User user);

    // 根据ID删除用户
    @Delete("DELETE FROM user WHERE user_id = #{userId}")
    void deleteById(@Param("userId") int userId);
}
