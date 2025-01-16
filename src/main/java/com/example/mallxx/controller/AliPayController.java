package com.example.mallxx.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.easysdk.factory.Factory;
import com.example.mallxx.config.AliPayConfig;
import com.example.mallxx.entity.Orders;
import com.example.mallxx.mapper.Order_detailsMapper;
import com.example.mallxx.mapper.OrdersMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Transactional(rollbackFor = Exception.class)
@Slf4j
@RequestMapping("/alipay")
public class AliPayController {

    @Resource
    AliPayConfig aliPayConfig;

    private final OrdersMapper ordersMapper;
    private final Order_detailsMapper Order_detailsMapper;
    public AliPayController (Order_detailsMapper orderDetailsMapper,OrdersMapper ordersMapper) {
        this.Order_detailsMapper = orderDetailsMapper;
        this.ordersMapper = ordersMapper;
    }


    //    @Resource
    private OrdersMapper shopOrderMapper;
    private static final String GATEWAY_URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private static final String FORMAT = "JSON";
    private static final String CHARSET = "utf-8";
    private static final String SIGN_TYPE = "RSA2";

    @GetMapping("/pay") // &subject=xxx&traceNo=xxx&totalAmount=xxx
    public void pay(String order_id,String total_amount,String shipping_address, HttpServletResponse httpResponse) throws Exception {
        System.out.println(order_id);
        System.out.println(total_amount);
        System.out.println(shipping_address);
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setBizContent("{\"out_trade_no\":\"" + order_id + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + shipping_address + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        request.setReturnUrl("https://localhost:8082/user_order_list");
        String form = "";
        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        // 直接将完整的表单html输出到页面
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");
            // 支付宝验签
            if (Factory.Payment.Common().verifyNotify(params)) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
                // 更新订单未已支付

                Order_detailsMapper.updateAfterPay("已支付",gmtPayment, Integer.parseInt(tradeNo));
                ordersMapper.updateAfterPay("已支付","支付宝", Integer.parseInt(tradeNo));
//                order.setCheckoutTime(params.get("gmt_payment"));
//                shopOrderMapper.updateById(order);
            }
        }
        return "success";
    }
}


