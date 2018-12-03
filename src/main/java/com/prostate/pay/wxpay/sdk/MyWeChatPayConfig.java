package com.prostate.pay.wxpay.sdk;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyWeChatPayConfig extends WXPayConfig {

    private byte[] certData;

    private String appid;
    private String mchid;
    private String key;

    public MyWeChatPayConfig(String appid, String mchid, String key, String certPath) throws Exception {
        this.appid = appid;
        this.mchid = mchid;
        this.key = key;

        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();


    }

    @Override
    public String getAppID() {
        return appid;
    }

    @Override
    public String getMchID() {
        return mchid;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return null;
    }
}
