package com.example.mallxx.mapper;

import com.example.mallxx.entity.Merchant;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface MerchantMapper {
    @Select("SELECT * FROM merchants WHERE username = #{username} AND password = #{password}")
    List<Merchant> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 获取所有商家信息
     */
    @Select("SELECT * FROM merchants")
    List<Merchant> findAll();

    /**
     * 根据id查询商家信息
     */
    @Select("SELECT * FROM merchants WHERE id = #{id}")
    Merchant findById(@Param("id") int id);

    /**
     *删除商家
     */
    @Delete("DELETE FROM merchants WHERE id = #{id}")
    Boolean deletemerchant(@Param("id") int id);

    /**
     *更新商家信息
     */

    @Update("UPDATE merchants SET username = #{merchant.username}, password = #{merchant.password}, " +
            "shop_name = #{merchant.shop_name}, contact_info = #{merchant.contact_info}, address = #{merchant.address} " +
            "WHERE id = #{merchant.id}")
    Boolean updateMerchant(@Param("merchant") Merchant merchant);

}
