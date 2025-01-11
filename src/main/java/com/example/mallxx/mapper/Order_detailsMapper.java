package com.example.mallxx.mapper;

import com.example.mallxx.entity.Order_details;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface Order_detailsMapper {
    //用户获取某一整个订单的所有details订单
    @Select("select * from order_details where order_id=#{order_id}")
    public List<Order_details> selectByOrderId(int order_id);

    //商家获取自家商铺所有订单
    @Select("select * from order_details where merchant_id=#{merchant_id}")
    public List<Order_details> selectByMerchantId(int merchant_id);

    //用户支付整个订单时将其中每一个details订单的支付状态更改
    @Update("update order_details set order_detail_status=#{order_detail_status},last_update_at=#{last_update_at}" +
            "where order_id=#{order_id}")
    public void updateOrder_detail_status(String order_detail_status,String last_update_at,int order_id);

    //用户更改某一个具体订单支付状态/请求退款、、、商家更改某一个具体订单支付状态/同意退款/已退款 按钮
    @Update("update order_details set order_detail_status=#{order_detail_status},last_update_at=#{last_update_at}" +
            "where order_detail_id=#{order_detail_id}")
    public void updateOrder_detail_status_One(String order_detail_status,String last_update_at,int order_detail_id);

    //用户更改某一个具体订单运输状态/已签收/运输中、、、、商家更改更改某一个具体订单运输状态/已出库/已回库 按钮
    @Update("update order_details set shipping_status=#{shipping_status},last_update_at=#{last_update_at}" +
            "where order_detail_id=#{order_detail_id}")
    public void updateShipping_status(String shipping_status,String last_update_at,int order_detail_id);

    @Insert("inser into order_details(order_id,product_id,quantity,unit_price,total_price,create_at,order_detail_status,shipping_status,last_update_at,merchant_id)" +
            "values(#{order_details.order_id},#{order_details.product_id},#{order_details.quantity},#{order_details.unit_price},#{order_details.total_price},#{order_details.create_at},#{order_details.order_detail_status},#{order_details.shipping_status},#{order_details.last_update_at},#{order_details.merchant_id})")
    public void insertOrder_details(Order_details order_details);
}
