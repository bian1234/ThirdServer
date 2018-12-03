package com.prostate.common.constant;

public class OcrConstants {
    //    获取签名所需信息

    //    生成签名所需信息必须使用主账号的，包括 APPID、Secret ID 和 Secret Key。

    public final static long APP_ID = 1256660245;
    public final static String SECRET_ID = "AKIDud8OQEt7D5i6F9ALiiXUCgvvgFfrdnSs";
    public final static String SECRET_KEY = "KPFtIRKxNZWEhvvhEWbXFhtpVvDG4cz7";

//    支持 http 和 https 两种协议：

//    http://recognition.image.myqcloud.com/ocr/idcard

//    https://recognition.image.myqcloud.com/ocr/idcard

    //身份证识别
    public final static String ID_CARD_HTTP_URL = "http://recognition.image.myqcloud.com/ocr/idcard";

    public final static String ID_CARD_HTTPS_URL = "https://recognition.image.myqcloud.com/ocr/idcard";

    //银行卡识别
    public final static String BANK_CARD_HTTP_URL = "http://recognition.image.myqcloud.com/ocr/bankcard";

    public final static String BANK_CARD_HTTPS_URL = "https://recognition.image.myqcloud.com/ocr/bankcard";

    //通用印刷体识别

    public final static String GENERAL_HTTP_URL = "http://recognition.image.myqcloud.com/ocr/general";

    public final static String GENERAL_HTTPS_URL = "https://recognition.image.myqcloud.com/ocr/general";

    //手写体识别

    public final static String HAND_WRITING_HTTP_URL = "http://recognition.image.myqcloud.com/ocr/handwriting";

    public final static String HAND_WRITING_HTTPS_URL = "https://recognition.image.myqcloud.com/ocr/handwriting";

    //名片识别
    public final static String BUSINESS_CARD_HTTP_URL = "http://recognition.image.myqcloud.com/ocr/businesscard";

    public final static String BUSINESS_CARD_HTTPS_URL = "https://recognition.image.myqcloud.com/ocr/businesscard";


}
