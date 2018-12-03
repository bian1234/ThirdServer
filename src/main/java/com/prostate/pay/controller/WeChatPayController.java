package com.prostate.pay.controller;

import com.prostate.common.base.BaseController;
import com.prostate.pay.entity.UnifiedOrderEntity;
import com.prostate.pay.service.WeChatPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "pay/weChat")
public class WeChatPayController extends BaseController {

    @Autowired
    private WeChatPayService weChatPayService;

    /**
     * 公众号支付
     *
     * @param unifiedOrderEntity
     * @return
     */
    @PostMapping(value = "unifiedOrder")
    public Map unifiedOrder(UnifiedOrderEntity unifiedOrderEntity) {

        Map<String, String> data = new HashMap();
//        data.put("body", "栗子医学-问诊费用支付);
//        data.put("out_trade_no", "2016090910595900000012");
//        data.put("device_info", "");
//        data.put("fee_type", "CNY");
//        data.put("total_fee", "1");
//        data.put("spbill_create_ip", "123.12.12.123");
//        data.put("notify_url", "http://www.example.com/wxpay/notify");
//        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
//        data.put("product_id", "12");
        data.put("body", unifiedOrderEntity.getBody());   //商品描述
        data.put("out_trade_no", unifiedOrderEntity.getOutTradeNo());   //订单号
        data.put("device_info", unifiedOrderEntity.getDeviceInfo());    //设备编号信息
        data.put("fee_type", unifiedOrderEntity.getFeeType());         //币种
        data.put("total_fee", unifiedOrderEntity.getTotalFee());       //价格
        data.put("spbill_create_ip", unifiedOrderEntity.getSpBillCreateIp());   //终端IP
        data.put("notify_url", unifiedOrderEntity.getNotifyUrl());    //通知地址
        data.put("trade_type", unifiedOrderEntity.getTradeType());  // 支付类型，此处指定为公众号支付
        data.put("product_id", unifiedOrderEntity.getProductId());   //产品id

        return weChatPayService.unifiedOrder(data);
    }
}
