package com.prostate.cos.service;

import com.prostate.common.constant.CosConstants;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CosService {


//    String appid = "1256660245";
//    String secretId = "AKIDud8OQEt7D5i6F9ALiiXUCgvvgFfrdnSs";
//    String secretKey = "KPFtIRKxNZWEhvvhEWbXFhtpVvDG4cz7";
//    String region_name = "ap-beijing";

    // 1 初始化用户身份信息(secretId, secretKey)
    COSCredentials cred = new BasicCOSCredentials(CosConstants.SECRET_ID, CosConstants.SECRET_KEY);
    // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
    ClientConfig clientConfig = new ClientConfig(new Region(CosConstants.REGION_NAME));
    // 3 生成cos客户端
    COSClient cosclient = new COSClient(cred, clientConfig);
    // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式


    public void uoload(String bucketName, File uploadFile, String key) {
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        // 指定要上传到 COS 上的路径
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, uploadFile);
        cosclient.putObject(putObjectRequest);

    }

    public void delete(String bucketName, String key) {
        cosclient.deleteObject(bucketName, key);
    }
//    public void download(){
//        // 指定要下载到的本地路径
//        File downFile = new File("src/main/resources/mydown.png");
//        // 指定要下载的文件所在的 bucket 和路径
//        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
//        ObjectMetadata downObjectMeta = cosclient.getObject(getObjectRequest, downFile);
//
//    }
}
