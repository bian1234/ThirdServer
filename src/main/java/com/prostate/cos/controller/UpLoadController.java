package com.prostate.cos.controller;

import com.prostate.common.base.BaseController;
import com.prostate.cos.service.CosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "cos")
public class UpLoadController extends BaseController {

    @Autowired
    private CosService cosService;

    private final static Map<String, String> bucketMap = new LinkedHashMap<>();

    UpLoadController() {
        bucketMap.put("checking-records", "checking-records-1256660245");
        bucketMap.put("checkup-records", "checkup-records-1256660245");
        bucketMap.put("diagnosis-records", "diagnosis-records-1256660245");
        bucketMap.put("disease-records", "disease-records-1256660245");
        bucketMap.put("hospital-records", "hospital-records-1256660245");
        bucketMap.put("inspection-records", "inspection-records-1256660245");
        bucketMap.put("doctor-sign", "doctor-sign-1256660245");
        bucketMap.put("user-head", "user-head-1256660245");
    }

    @PostMapping(value = "upload")
    public Map upload(@RequestParam("file") MultipartFile file, String recordType) {
        String key = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String bucketName = bucketMap.get(recordType);
        log.info("bucketName=" + bucketName);

        if (!file.isEmpty()) {

            File f = null;
            try {
                f = File.createTempFile("tmp", null);
                file.transferTo(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            f.deleteOnExit();

            cosService.uoload(bucketName, f, key);

            StringBuffer imgPath = new StringBuffer();
            imgPath.append("https://");
            imgPath.append(bucketName);
            imgPath.append(".cosbj.myqcloud.com/");
            imgPath.append(key);

            return upLoadSuccessResponse(imgPath.toString());
        }
        return emptyParamResponse();
    }

    /**
     * 删除 上传的图片
     *
     * @param imgPath
     * @return
     */
    @PostMapping(value = "delete")
    public Map delete(String imgPath) {

        String bucketName = imgPath.substring(8, imgPath.indexOf("."));
        String key = imgPath.substring(imgPath.lastIndexOf("/")+1);

        cosService.delete(bucketName, key);

        return deleteSuccseeResponse();
    }
}
