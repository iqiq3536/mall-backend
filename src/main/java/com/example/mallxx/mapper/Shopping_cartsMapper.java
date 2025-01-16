package com.example.mallxx.mapper;

import com.example.mallxx.entity.Shopping_carts;
import com.example.mallxx.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface Shopping_cartsMapper {
    @Insert("insert into Shopping_carts(user_id) values(#{user_id})" )
    void add(@Param("user_id") int user_id);

    @Select("select * from Shopping_carts where user_id=#{user_id}")
    Shopping_carts findByUser_id(@Param("user_id") int user_id);
}
