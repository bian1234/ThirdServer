package com.prostate.ocr.controller;

import com.prostate.common.base.BaseController;
import com.prostate.common.constant.OcrConstants;
import com.prostate.ocr.service.impl.HttpAPIService;
import com.prostate.ocr.util.OcrUtils;
import com.prostate.ocr.util.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "ocr")
public class OcrController extends BaseController {
    @Autowired
    private HttpAPIService httpAPIService;


    /**
     * 腾讯省份证识别
     *
     * @param url
     * @return
     * @throws Exception
     */
    @GetMapping(value = "idCard")
    public Map<String, Object> idCard(String url) throws Exception {

        String authorization = SignUtils.appSign(OcrConstants.APP_ID, OcrConstants.SECRET_ID, OcrConstants.SECRET_KEY, 18000);

        String idCardResult = httpAPIService.doPost(OcrConstants.ID_CARD_HTTP_URL, url, authorization, "application/json", "recognition.image.myqcloud.com");
        if (idCardResult == null) {
            return insertFailedResponse();
        }
        Map<String, Object> idCardInfo = OcrUtils.getIdCardInfo(idCardResult);
        log.info("---------------------------------------------------------------");
        return insertSuccseeResponse(idCardInfo);
    }

//    /**
//     * 身份证识别
//     *
//     * @return
//     * @throws Exception
//     */
//    @PostMapping(value = "idcard")
//    public Map idCard(File file) throws Exception {
////        @RequestParam("cos") MultipartFile cos,
//
//
////        File cos = new File("https://checking-records-1256660245.cos.ap-beijing.myqcloud.com/IDCard_up.jpg");
////        File file1 = new File(cos.getInputStream());
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("appid", CosConstants.APP_ID);
//        String idCardResult = httpAPIService.doPost(CosConstants.ID_CARD_HTTP_URL, jsonObj, file);
//
//        if (idCardResult == null) {
//            return insertFailedResponse();
//        }
//        Map<String, Object> idCardInfo = OcrUtils.getIdCardInfo(idCardResult);
//
//        return insertSuccseeResponse(idCardInfo);
//    }
//
//    /**
//     * 银行卡识别
//     */
//    @GetMapping(value = "bankcard")
//    public Map bankCard(String url) throws Exception {
//
//        File file = new File("C:\\Users\\Administrator\\Desktop\\beij_bank.jpg");
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("appid", CosConstants.APP_ID);
//
//        String bankCardResult = httpAPIService.doPost(CosConstants.BANK_CARD_HTTP_URL, jsonObj, file);
//        if (bankCardResult == null) {
//            return insertFailedResponse();
//        }
//        Map<String, Object> idCardInfo = OcrUtils.getBankCardInfo(bankCardResult);
//
//        return insertSuccseeResponse(idCardInfo);
//    }
//
//
//    /**
//     * 印刷体文字 识别
//     *
//     * @return
//     * @throws Exception
//     */
//    @GetMapping(value = "general")
//    public Map general() throws Exception {
//
//        File file = new File("C:\\Users\\Administrator\\Desktop\\general.jpg");
//
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("appid", CosConstants.APP_ID);
//
//        String generalResult = httpAPIService.doPost(CosConstants.GENERAL_HTTP_URL, jsonObj, file);
//
//        if (generalResult == null) {
//            return insertFailedResponse();
//        }
//        List<String> stringList = OcrUtils.getText(generalResult);
//
//        return insertSuccseeResponse(stringList);
//    }
//
//
//    /**
//     * 手写体识别
//     * @return
//     * @throws Exception
//     */
//    @GetMapping(value = "handwriting")
//    public Map handwriting() throws Exception {
//
//        File file = new File("C:\\Users\\Administrator\\Desktop\\handwriting.jpg");
//
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("appid", CosConstants.APP_ID);
//
//        String handwritingResult = httpAPIService.doPost(CosConstants.HAND_WRITING_HTTP_URL, jsonObj, file);
//
//        if (handwritingResult == null) {
//            return insertFailedResponse();
//        }
//        List<String> stringList = OcrUtils.getText(handwritingResult);
//
//        return insertSuccseeResponse(stringList);
//    }
//
//
//    @GetMapping(value = "businesscard")
//    public Map businesscard() throws Exception {
//
//        File file = new File("C:\\Users\\Administrator\\Desktop\\businesscard.jpg");
//
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("appid", CosConstants.APP_ID);
//
//        String handwritingResult = httpAPIService.doPost(CosConstants.HAND_WRITING_HTTP_URL, jsonObj, file);
//
//        if (handwritingResult == null) {
//            return insertFailedResponse();
//        }
//        List<String> stringList = OcrUtils.getText(handwritingResult);
//
//        return insertSuccseeResponse(stringList);
//    }
}
