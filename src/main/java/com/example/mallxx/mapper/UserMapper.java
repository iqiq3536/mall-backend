package com.example.mallxx.mapper;

import com.example.mallxx.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    // 查询所有用户
    @Select("SELECT * FROM users")
    List<User> findAll();

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    User loginUser(@Param("username") String username, @Param("password") String password);

    // 根据ID查询用户
    @Select("SELECT * FROM users WHERE user_id = #{user_id}")
    User findById(@Param("user_id") int user_id);
    // 根据用户名查询用户
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(@Param("username") String username);
    // 根据全名查询用户
    @Select("SELECT * FROM users WHERE full_name = #{full_name}")
    User findByFull_name(@Param("full_name") String full_name);
    //根据家庭ID查用户
    @Select("SELECT * FROM users WHERE family_id = #{familyId}")
    List<User> findUsersByFamilyId(@Param("familyId") Integer familyId);
    //根据家庭名称查用户
    @Select("SELECT * FROM users WHERE family_name = #{familyName}")
    List<User> findUsersByFamilyName(@Param("familyName") String familyName);

    // 插入新用户
    @Insert("INSERT INTO users(username, password, full_name, gender, contact_info) " +
            "VALUES(#{user.username}, #{user.password}, #{user.full_name}, #{user.gender}, #{user.contact_info})")
    Boolean addUser(@Param("user") User user);
    // 检查用户名是否已存在
    @Select("SELECT COUNT(*) > 0 FROM users WHERE username = #{username}")
    Boolean checkUserExists(@Param("username") String username);

    // 更新用户信息
    @Update("UPDATE users SET username = #{user.username}, password = #{user.password}, full_name = #{user.full_name}, " +
            "gender = #{user.gender}, contact_info = #{user.contact_info}, family_id = #{user.family_id}, family_name = #{user.family_name} " +
            "WHERE user_id = #{user.user_id}")
    Boolean updateUser(@Param("user") User user);


    // 删除用户信息
    @Delete("DELETE FROM users WHERE user_id = #{user_id}")
    Boolean deleteUser(@Param("user_id") int userId);
}
