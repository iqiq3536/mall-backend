package com.example.mallxx.mapper;

import com.example.mallxx.entity.Cart_details;
import com.example.mallxx.entity.Shopping_carts;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface Cart_detailsMapper {
    //用户操作
    @Insert("INSERT INTO cart_details(cart_details_id,cart_id,product_id,quantity,unit_price,creat_data)" +
            "values (#{cart_details.cart_details_id},#{cart_details.cart_id},#{cart_details.product_id},#{cart_details.quantity},#{cart_details.unit_price},#{cart_details.creat_data})")
    void Insert(@Param("cart_details") Cart_details cart_details);

    @Delete("DELETE from cart_details where cart_details_id=#{cart_details.cart_details_id}")
    void DeleteByCart_details_id(@Param("cart_details") Cart_details cart_details);

    //根据Shopping_carts.cart_id清空购物车
    @Delete("DELETE from cart_details where cart_id=#{Shopping_carts.cart_id}")
    void DeleteByCart_id(@Param("shopping_carts") Shopping_carts shopping_carts);

    //更改数量
    @Update("Update cart_details SET cart_details_id=#{cart_details.cart_details_id},cart_id=#{cart_details.cart_id},product_id=#{cart_details.product_id},quantity=#{cart_details.quantity},unit_price=#{cart_details.unit_price},creat_data=#{cart_details.creat_data}")
    void Update(@Param("cart_details") Cart_details cart_details);

    //根据Cart_details.cart_details_id获取购物车内具体商品信息
    @Select("SELECT * FROM cart_details where cart_details_id = #{cart_details.cart_details_id}")
    Cart_details findCart_detailsByCart_details_id(@Param("cart_details") Cart_details cart_details);

    //根据Shopping_carts.cart_id获取用户购物车具体信息
    @Select("SELECT * FROM cart_details where cart_id = #{Shopping_carts.cart_id}")
    List<Cart_details> findCart_detailsByCart_id(@Param("shopping_carts") Shopping_carts shopping_carts);
}
