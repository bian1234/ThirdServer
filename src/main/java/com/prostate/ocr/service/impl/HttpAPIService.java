package com.prostate.ocr.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.prostate.common.constant.CosConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

@Slf4j
@Component
public class HttpAPIService {

    private final CloseableHttpClient httpClient;

    private final RequestConfig config;


    @Autowired
    public HttpAPIService(CloseableHttpClient httpClient, RequestConfig config) {
        this.httpClient = httpClient;
        this.config = config;
    }

    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        // 判断状态码是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            // 返回响应体的内容
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return null;
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());

    }

    /**
     * 单张省份证照片识别
     * @param url
     * @param authorization
     * @param contentType
     * @param host
     * @return
     * @throws Exception
     */
    @Cacheable(value = "idCard_ocr",key = "#imgurl",unless="#result == null")
    public String doPost(String url, String imgurl,String authorization,String contentType,String host) throws Exception {
        log.info("==================调用腾讯OCR身份证识别==================");
        HttpPost httpPost = new HttpPost(url);
        try {
            String[] url_list = {imgurl};
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("appid", CosConstants.APP_ID);
            jsonObj.put("url_list", url_list);

            log.info(jsonObj.toString());

            StringEntity s = new StringEntity(jsonObj.toString(), Charset.forName("UTF-8"));

            httpPost.setEntity(s);
            httpPost.setHeader("Host", host);
            httpPost.setHeader("Content-Type", contentType);
            httpPost.setHeader("Authorization", authorization);
            HttpResponse response = this.httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == 200) {
                String resultStr = EntityUtils.toString(response.getEntity(), "UTF-8");

//                log.info(resultStr);

                return resultStr;

            } else {
                String err = response.getStatusLine().getStatusCode() + "";
                log.info("发送失败:" + err);
                log.info("发送失败:" + EntityUtils.toString(response.getEntity(), "UTF-8"));
                return null;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

//    /**
//     * POST 发送 multipart/form-data 数据
//     */
//    public String doPost(String url, JSONObject jsonObj, File file) throws Exception {
//        HttpPost httpPost = new HttpPost(url);
//        try {
//
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//
//            for (String s : jsonObj.keySet()) {
//                builder.addTextBody(s, jsonObj.get(s).toString(), ContentType.TEXT_PLAIN);
//            }
//            builder.addBinaryBody(
//                    "image",
//                    new FileInputStream(file),
//                    ContentType.APPLICATION_OCTET_STREAM,
//                    file.getName()
//            );
//
//            HttpEntity httpEntity = builder.build();
//
//            httpPost.setEntity(httpEntity);
//
//            String si = SignUtils.appSign(1256660245, CosConstants.SECRET_ID, CosConstants.SECRET_KEY, 18000);
//            log.info(si);
//            httpPost.setHeader("Host", "recognition.image.myqcloud.com");
////            httpPost.setHeader("Content-Type", "multipart/form-data");
//            httpPost.setHeader("Authorization", si);
//
//            HttpResponse response = this.httpClient.execute(httpPost);
//
//            if (response.getStatusLine().getStatusCode() == 200) {
//                String resultStr = EntityUtils.toString(response.getEntity(), "UTF-8");
//                log.info(resultStr);
//                return resultStr;
//            } else {
//                String err = response.getStatusLine().getStatusCode() + "";
//                log.info("发送失败:" + err);
//                log.info("发送失败:" + EntityUtils.toString(response.getEntity(), "UTF-8"));
//                return null;
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//
//        }
//        return null;
//    }


}