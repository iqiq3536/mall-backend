package com.example.mallxx.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.example.mallxx.entity.Admin;
import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("SELECT * FROM administrators WHERE admin_id = #{adminId}")
    Admin selectById(int adminId);

    @Select("SELECT * FROM administrators")
    List<Admin> selectAll();

    @Insert("INSERT INTO administrators(admin_id, username, password) VALUES(#{adminId}, #{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "adminId")
    int insert(Admin admin);

    @Update("UPDATE administrators SET username=#{username}, password=#{password} WHERE admin_id=#{adminId}")
    int update(Admin admin);

    @Delete("DELETE FROM administrators WHERE admin_id=#{adminId}")
    int deleteById(Integer adminId);
}
