package com.prostate.pay.service;

import com.prostate.pay.wxpay.sdk.WXPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeChatPayServiceImpl implements WeChatPayService {

    @Autowired
    private WXPay weChatPay;


    @Override
    public Map<String, String> unifiedOrder(Map<String, String> reqDate) {


        try {
            return weChatPay.unifiedOrder(reqDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
