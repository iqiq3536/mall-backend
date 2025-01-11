package com.example.mallxx.controller;

import com.example.mallxx.entity.Cart_details;
import com.example.mallxx.entity.Order_details;
import com.example.mallxx.mapper.Order_detailsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order_details")
public class Order_detailsController {
    private  final Order_detailsMapper order_detailsMapper;
    public Order_detailsController(Order_detailsMapper order_detailsMapper) {
        this.order_detailsMapper = order_detailsMapper;
    }

    @GetMapping("/getOrder_details_list")
    public List<Order_details> getOrder_details(@Param("order_id") int order_id){
        List<Order_details> order_details_list=order_detailsMapper.selectByOrderId(order_id);
        return order_details_list;
    }

    @GetMapping("/getOrder_details_merchant_list")
    public List<Order_details> getOrder_details_merchant(@Param("merchant_id") int merchant_id){
        List<Order_details> order_details_list=order_detailsMapper.selectByMerchantId(merchant_id);
        return order_details_list;
    }

    @PostMapping("/update_Allpaystatus")
    public void update_Allpaystatus(@Param("order_detail_status") String order_detail_status,
                                 @Param("last_update_at") String last_update_at,
                                 @Param("order_id") int order_id){
        order_detailsMapper.updateOrder_detail_status(order_detail_status,last_update_at,order_id);
    }

    @PostMapping("/update_Onepaystatus")
    public void update_Onepaystatus(@Param("order_detail_status") String order_detail_status,
                                 @Param("last_update_at") String last_update_at,
                                 @Param("order_detail_id") int order_detail_id){
        order_detailsMapper.updateOrder_detail_status(order_detail_status,last_update_at,order_detail_id);
    }

    @PostMapping("/update_Oneshipstatus")
    public void update_Oneshipstatus(@Param("shipping_status") String shipping_status,
                                    @Param("last_update_at") String last_update_at,
                                    @Param("order_detail_id") int order_detail_id){
        order_detailsMapper.updateOrder_detail_status(shipping_status,last_update_at,order_detail_id);
    }


}

////////还需调整货物数量
//