package com.example.mallxx.mapper;

import com.example.mallxx.entity.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SellerMapper {
    @Select("select * from seller")
    List<Seller> findAll();

    //@Select("SELECT * FROM seller WHERE id = #{username} AND name = #{password}")
    //List<Seller> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    List<Seller> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


}
