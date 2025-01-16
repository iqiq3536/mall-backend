package com.example.mallxx.controller;

import com.example.mallxx.entity.Cart_details;
import com.example.mallxx.entity.Orders;
import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.OrdersMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private final OrdersMapper ordersMapper;
    public OrdersController(OrdersMapper ordersMapper) {
        this.ordersMapper = ordersMapper;
    }

    //成功
    @GetMapping("/getOrders_list")
    public List<Orders> getOrdersList(@CookieValue(value = "user_id", required = false)String user_id) {
        List<Orders> orders_list=ordersMapper.selectByUserId(Integer.parseInt(user_id));
        System.out.println(orders_list);
        return orders_list;
    }

    //成功
    @GetMapping("/getOrders_listByOrder_status")
    public List<Orders> getOrdersListByOrder_status(@CookieValue(value = "user_id", required = false)String user_id,  @Param("order_status") String order_status) {
        List<Orders> orders=ordersMapper.selectByOrderStatus(Integer.parseInt(user_id),order_status);
        System.out.println(orders);
        return orders;
    }

    //成功
    @PostMapping("/insertOrders")
    public void insertOrders(@CookieValue(value = "user_id", required = false)String user_id,@Param("total_amount") String total_amount) {
        Orders orders=new Orders();
        orders.setOrder_status("未支付");
        orders.setUser_id(Integer.parseInt(user_id));
        orders.setTotal_amount(Double.parseDouble(total_amount));
        orders.setCreate_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println(orders);
        ordersMapper.insert(orders);
    }

    //成功
    @PostMapping("/updateOrders_status")
    public void updateOrders_status(@Param("order_status") String order_status,@Param("order_id") String order_id) {
        ordersMapper.update(order_status,Integer.parseInt(order_id));
    }

    //成功
    @PostMapping("/deleteOrder")
    public void deleteOrder(@Param("order_id") String order_id) {
        ordersMapper.delete(Integer.parseInt(order_id));
    }

    //成功
    @PostMapping("/updateShopping_address")
    public void update_Shopping_address(@RequestBody Map<String, Object> params){
        String shipping_address = (String) params.get("shipping_address");
        String order_id = String.valueOf(params.get("order_id"));
        System.out.println(order_id);
        System.out.println(shipping_address);
        ordersMapper.updateShipping_address(shipping_address,Integer.parseInt(order_id));
    }
}
