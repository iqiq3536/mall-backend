package com.example.mallxx.controller;

import com.example.mallxx.entity.Cart_details;
import com.example.mallxx.entity.Orders;
import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.OrdersMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private final OrdersMapper ordersMapper;
    public OrdersController(OrdersMapper ordersMapper) {
        this.ordersMapper = ordersMapper;
    }

    @GetMapping("/getOrders_list")
    public List<Orders> getOrdersList(@RequestBody User user) {
        List<Orders> orders=ordersMapper.selectByUserId(user);
        return orders;
    }

    @GetMapping("/getOrders_listByOrder_status")
    public List<Orders> getOrdersListByOrder_status(@RequestBody User user,  @Param("order_status") String order_status) {
        List<Orders> orders=ordersMapper.selectByOrderStatus(user,order_status);
        return orders;
    }

    @PostMapping("/insertOrders")
    public void insertOrders(@RequestBody List<Cart_details> cart_details) {
        //int order_id=ordersMapper.insert(orders);
        //还需删除购物车选中部分
        //还需insert order_details
    }

    @PostMapping("/updateOrders_status")
    public void updateOrders_status(@Param("order_status") String order_status) {
        ordersMapper.update(order_status);
    }

    @PostMapping("/deleteOrder")
    public void deleteOrder(@RequestBody Orders orders) {
        ordersMapper.delete(orders);
    }
}
