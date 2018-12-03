package com.prostate.common.config;

import com.prostate.pay.wxpay.sdk.MyWeChatPayConfig;
import com.prostate.pay.wxpay.sdk.WXPay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeChatPayConfig {

    @Value("${weChat.pay.appid}")
    private String appid;

    @Value("${weChat.pay.mchid}")
    private String mchid;

    @Value("${weChat.pay.key}")
    private String key;

    @Value("${weChat.pay.certPath}")
    private String certPath;

    @Bean(name = "myWeChatPayConfig")
    public MyWeChatPayConfig getMyWeChatPayConfig() {
        MyWeChatPayConfig myWeChatPayConfig = null;
        try {
            myWeChatPayConfig = new MyWeChatPayConfig(appid, mchid, key, certPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myWeChatPayConfig;
    }

    @Bean(name = "weChatPay")
    public WXPay getWeChatPay(MyWeChatPayConfig myWeChatPayConfig) {

        WXPay weChatPay = null;
        try {
            weChatPay = new WXPay(myWeChatPayConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weChatPay;
    }
}
