package com.example.mallxx.mapper;

import com.example.mallxx.entity.Shopping_carts;
import com.example.mallxx.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface Shopping_cartsMapper {
    @Insert("insert into Shopping_carts(user_id) values(#{user.user_id})" )
    void add(@Param("user") User user);

    @Select("select * from Shopping_carts where user_id=#{user.user_id}")
    Shopping_carts findByUser_id(@Param("user") User user);
}
