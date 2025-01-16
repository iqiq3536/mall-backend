package com.example.mallxx.mapper;

import com.example.mallxx.entity.CartDetailsWithProduct;
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

    /**
     * SELECT
     *     cd.cart_details_id,
     *     sc.cart_id,
     *     cd.product_id,
     *     cd.quantity,
     *     cd.unit_price,
     *     cd.merchant_id
     * FROM
     *     shopping_carts AS sc
     * JOIN
     *     cart_details AS cd ON sc.cart_id = cd.cart_id
     * WHERE
     *     sc.user_id = 1; -- 这里用?表示参数位置，实际执行时需要替换为具体的user_id值
     */
    @Select("SELECT " +
            "    sc.user_id, " +
            "    cd.cart_details_id, " +
            "    sc.cart_id, " +
            "    cd.product_id, " +
            "    cd.quantity, " +
            "    cd.unit_price, " +
            "    cd.merchant_id, " +
            "    p.name AS product_name, " +
            "    p.img_url " +
            "FROM " +
            "    shopping_carts AS sc " +
            "JOIN " +
            "    cart_details AS cd ON sc.cart_id = cd.cart_id " +
            "JOIN " +
            "    products AS p ON cd.product_id = p.id " +
            "WHERE " +
            "    sc.user_id = #{user_id} "
    )
    List<CartDetailsWithProduct> findByUser_id2(@Param("user_id") int user_id);

    /**
     * SELECT
     *     u.user_id,
     *     u.username,
     *     cd.cart_details_id,
     *     sc.cart_id,
     *     p.id AS product_id,
     *     cd.quantity,
     *     cd.unit_price,
     *     cd.merchant_id,
     *     p.name AS product_name,
     *     p.img_url
     * FROM
     *     users u
     * JOIN
     *     shopping_carts sc ON u.user_id = sc.user_id
     * JOIN
     *     cart_details cd ON sc.cart_id = cd.cart_id
     * JOIN
     *     products p ON cd.product_id = p.id
     * WHERE
     *     u.family_id = (SELECT family_id FROM users WHERE user_id = 1) -- 这里用?表示参数位置，实际执行时需要替换为具体的user_id值
     * ORDER BY
     *     u.user_id, sc.cart_id, cd.cart_details_id;
     */
    @Select("SELECT " +
            "    u.user_id, " +
            "    u.username, " +
            "    cd.cart_details_id, " +
            "    sc.cart_id, " +
            "    p.id AS product_id, " +
            "    cd.quantity, " +
            "    cd.unit_price, " +
            "    cd.merchant_id, " +
            "    p.name AS product_name, " +
            "    p.img_url " +
            "FROM " +
            "    users u " +
            "JOIN " +
            "    shopping_carts sc ON u.user_id = sc.user_id " +
            "JOIN " +
            "    cart_details cd ON sc.cart_id = cd.cart_id " +
            "JOIN " +
            "    products p ON cd.product_id = p.id " +
            "WHERE " +
            "    u.family_id = (SELECT family_id FROM usersWHERE user_id = #{user_id}) " +
            "ORDER BY " +
            "    u.user_id, sc.cart_id, cd.cart_details_id;"
            )
    List<CartDetailsWithProduct> findByUser_id3(@Param("user_id") int user_id);
}
