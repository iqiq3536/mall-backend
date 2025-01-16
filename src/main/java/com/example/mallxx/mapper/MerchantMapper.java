package com.example.mallxx.mapper;

import com.example.mallxx.entity.Merchant;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MerchantMapper {
    @Select("SELECT * FROM Merchants WHERE username = #{username} AND password = #{password}")
    List<Merchant> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


}
