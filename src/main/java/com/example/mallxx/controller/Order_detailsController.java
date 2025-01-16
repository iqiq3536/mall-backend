package com.example.mallxx.controller;

import com.example.mallxx.entity.Cart_details;
import com.example.mallxx.entity.Order_details;
import com.example.mallxx.entity.Product;
import com.example.mallxx.mapper.Order_detailsMapper;
import com.example.mallxx.mapper.ProductMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.example.mallxx.tools.RandomStringGenerator;

@RestController
@RequestMapping("/api/order_details")
public class Order_detailsController {

    private final ProductMapper ProductMapper;
    private final Order_detailsMapper order_detailsMapper;
    public Order_detailsController(Order_detailsMapper order_detailsMapper, ProductMapper ProductMapper) {
        this.order_detailsMapper = order_detailsMapper;
        this.ProductMapper = ProductMapper;
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
        System.out.println(merchant_id+"+-+-+-++++-+++++-+");
        List<Order_details> order_details_list=order_detailsMapper.selectByMerchantId(Integer.parseInt(merchant_id));
        System.out.println(order_details_list+"+++++++++++++++");
        return order_details_list;
    }

    /**
     * 通过productId获取商品名称和图片URL
     */
    @PostMapping("/get_product")
    public ResponseEntity<Product> get_product(@RequestBody Map<String, Object> requestBody) {
        // 假设 productId 是一个字符串类型的参数
        //System.out.println("***********************************++++");
        //System.out.println(requestBody);
        //System.out.println(requestBody.get("product_id"));
        int productId = Integer.parseInt(requestBody.get("product_id").toString());
        // 调用方法获取商品信息
        Product productInfo = ProductMapper.getProductNameAndImgUrlById(productId);
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        System.out.println(productInfo);
        // 检查是否成功获取到了数据

            return ResponseEntity.ok(productInfo);
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
    public void updateOnepaystatus(@RequestBody Map<String, Object> requestBody){
        String orderDetailStatus = (String)requestBody.get("order_detail_status");
        Integer orderDetailId = (Integer)requestBody.get("order_detail_id"); // 修改为Integer类型

        System.out.println(orderDetailStatus+"+++++++++++++++++++++++"+orderDetailId);
        Date date = new Date();
        // 创建SimpleDateFormat对象，指定日期时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 使用SimpleDateFormat对象格式化Date对象，得到当前日期时间字符串
        String last_update_at = sdf.format(date);
        order_detailsMapper.updateOrder_detail_status_One(orderDetailStatus, last_update_at, orderDetailId);
    }


    //成功
    @PutMapping("/update_Oneshipstatus")
    public void update_Oneshipstatus(@RequestBody Map<String, Object> requestBody) {
        String shipping_status = (String) requestBody.get("shipping_status");
        Integer order_detail_id = (Integer) requestBody.get("order_detail_id"); // 修改为Integer类型

        Date date = new Date();
        // 创建SimpleDateFormat对象，指定日期时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 使用SimpleDateFormat对象格式化Date对象，得到当前日期时间字符串
        String last_update_at = sdf.format(date);
        order_detailsMapper.updateShipping_status(shipping_status, last_update_at, order_detail_id);
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