package com.example.mallxx.mapper;

import org.apache.ibatis.annotations.*;
import com.example.mallxx.entity.Orders;
import com.example.mallxx.entity.User;
import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from orders where user_id=#{user.user_id}")
    public List<Orders> selectByUserId(User user);

    @Select("select * from orders where user_id=#{user.user_id} and order_status=#{order_status}")
    public List<Orders> selectByOrderStatus(User user, String order_status);

    @Insert("insert into orders(order_id,user_id,order_status,create_at,total_amount,payment_method,shipping_address)" +
            "values(#{orders.order_id},#{orders.user_id},#{orders.order_status},#{orders.create_at},#{orders.total_amount},#{orders.payment_method},#{orders.shipping_address})")
    public void insert(@Param("orders") Orders orders);


}
