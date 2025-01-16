package com.example.mallxx.mapper;

import org.apache.ibatis.annotations.*;
import com.example.mallxx.entity.Orders;
import com.example.mallxx.entity.User;
import java.util.List;

@Mapper
public interface OrdersMapper {
    @Select("select * from orders where user_id=#{user_id}")
    public List<Orders> selectByUserId(int user_id);

    @Select("select * from orders where user_id=#{user_id} and order_status=#{order_status}")
    public List<Orders> selectByOrderStatus(int user_id, String order_status);

    //成功
    @Insert("insert into orders(user_id,order_status,create_at,total_amount,payment_method,shipping_address)" +
            "values(#{orders.user_id},#{orders.order_status},#{orders.create_at},#{orders.total_amount},#{orders.payment_method},#{orders.shipping_address})")
    public int insert(@Param("orders") Orders orders);

    @Update("update orders set order_status=#{order_status} where order_id=#{order_id}")
    public void update(@Param("order_status") String order_status, @Param("order_id") int order_id);

    @Delete("delete from orders where order_id=#{order_id}")
    public void delete(@Param("order_id") int order_id);

    @Update("update orders set shipping_address=#{shipping_address} where order_id=#{order_id}")
    public void updateShipping_address(String shipping_address,int order_id);

    //更改order_id的所有支付状态以及支付方式
    @Update("update orders set order_status=#{order_status},payment_method=#{payment_method}" +
            "where order_id=#{order_id}")
    static void updateAfterPay(String order_status,String payment_method,int order_id){
        return;
    }
}
