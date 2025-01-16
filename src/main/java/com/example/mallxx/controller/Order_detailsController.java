package com.example.mallxx.controller;

import com.example.mallxx.entity.Cart_details;
import com.example.mallxx.entity.Order_details;
import com.example.mallxx.mapper.Order_detailsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.example.mallxx.tools.RandomStringGenerator;

@RestController
@RequestMapping("/api/order_details")
public class Order_detailsController {
    private final Order_detailsMapper order_detailsMapper;
    public Order_detailsController(Order_detailsMapper order_detailsMapper) {
        this.order_detailsMapper = order_detailsMapper;
    }

    //成功
    @PostMapping("/insert_one")
    public void insertOne(@RequestBody Order_details order_details,@CookieValue(value = "user_id", required = false)String user_id) {
        System.out.println(order_details);
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String time2 = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        order_details.setCreate_at(time);
        order_details.setLast_update_at(time);
        order_details.setOrderNo(time2+RandomStringGenerator.generateRandomString(5));
        order_details.setOrder_detail_status("未支付");
        order_details.setUser_id(Integer.parseInt(user_id));
        System.out.println(order_details);
        order_detailsMapper.insertOrder_details(order_details);
    }

    //成功
    @GetMapping("/getOrder_details_list")
    public List<Order_details> getOrder_details(@Param("order_id") String order_id){
        List<Order_details> order_details_list=order_detailsMapper.selectByOrderId(Integer.parseInt(order_id));
        System.out.println(order_details_list);
        return order_details_list;
    }

    //成功
    @GetMapping("/getOrder_details_merchant_list")
    public List<Order_details> getOrder_details_merchant(@CookieValue(value = "merchantId", required = false)String merchant_id){
        List<Order_details> order_details_list=order_detailsMapper.selectByMerchantId(Integer.parseInt(merchant_id));
        System.out.println(order_details_list);
        return order_details_list;
    }

    //成功
    @PostMapping("/update_Allpaystatus")
    public void update_Allpaystatus(@Param("order_detail_status") String order_detail_status,
                                 @Param("order_id") String order_id){
        String last_update_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        order_detailsMapper.updateOrder_detail_status(order_detail_status,last_update_at,Integer.parseInt(order_id));
    }

    //成功
    @PutMapping("/update_Onepaystatus")
    public void update_Onepaystatus(@RequestBody Map<String, Object> params) {
        String order_detail_status = (String) params.get("order_detail_status");
        String order_detail_id = String.valueOf(params.get("order_detail_id"));
        Date date = new Date();
        // 创建SimpleDateFormat对象，指定日期时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 使用SimpleDateFormat对象格式化Date对象，得到当前日期时间字符串
        String last_update_at = sdf.format(date);
        System.out.println(order_detail_status);
        System.out.println(order_detail_id);
        order_detailsMapper.updateOrder_detail_status_One(order_detail_status,last_update_at,Integer.parseInt(order_detail_id));
    }

    //成功
    @PutMapping("/update_Oneshipstatus")
    public void update_Oneshipstatus(@RequestBody Map<String, Object> params){
        String shipping_status = (String) params.get("shipping_status");
        String order_detail_id = String.valueOf(params.get("order_detail_id"));
        Date date = new Date();
        // 创建SimpleDateFormat对象，指定日期时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 使用SimpleDateFormat对象格式化Date对象，得到当前日期时间字符串
        String last_update_at = sdf.format(date);
        order_detailsMapper.updateShipping_status(shipping_status,last_update_at,Integer.parseInt(order_detail_id));
    }

    //成功
    @PostMapping("/updateShopping_address")
    public void update_Shopping_address(@RequestBody Map<String, Object> params){
        String shipping_address = (String) params.get("shipping_address");
        String order_id = String.valueOf(params.get("order_id"));
        System.out.println(order_id);
        System.out.println(shipping_address);
        order_detailsMapper.updateShipping_address(shipping_address,Integer.parseInt(order_id));
    }

}

////////还需调整货物数量
//