package com.prostate.pay.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UnifiedOrderEntity {

    private String body;  //商品描述 String(128)

    private String outTradeNo; //商户订单号 String(32)

    private String deviceInfo; //设备号 String(32)

    private String feeType; // 标价币种 String(16)

    private String totalFee; // 标价金额 Int

    private String spBillCreateIp; // 终端IP String(16)

    private String notifyUrl; // 通知地址 String(256)

    private String tradeType; // 交易类型 String(16)

    private String productId; // 商品ID String(32)


}
