package com.example.mallxx.mapper;

import com.example.mallxx.entity.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface MerchantMapper {
    @Select("SELECT * FROM Merchants WHERE username = #{username} AND password = #{password}")
    List<Merchant> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 获取所有商家信息
     */
    @Select("SELECT * FROM Merchants")
    List<Merchant> findAll();

    /**
     * 根据id查询商家信息
     */
    @Select("SELECT * FROM Merchants WHERE id = #{id}")
    Merchant findById(@Param("id") int id);

    /**
     * 通过商家id删除商家
     */
    @Select("DELETE FROM Merchants WHERE id = #{id}")
    void deleteById(@Param("id") int id);

    /**
     *更新商家信息
     */
    @Update("UPDATE Merchants SET username = #{username}, password = #{password}, " +
            "shop_name = #{shop_name}, contact_info = #{contact_info}, address = #{address} WHERE id = #{id}")
    void update(@Param("id") int id, @Param("username") String username,
                @Param("password") String password, @Param("shop_name") String shop_name,
                @Param("contact_info") String contact_info, @Param("address") String address);

}
