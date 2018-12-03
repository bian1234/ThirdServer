package com.prostate.ocr.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class OcrUtils {

    /**
     * 腾讯OCR身份识别 结果处理工具
     *
     * @param idCardResult
     */
    public static Map<String, Object> getIdCardInfo(String idCardResult) {
        //
        Map<String, Object> idCardMaps = JSON.parseObject(idCardResult);

        List list = (List) idCardMaps.get("result_list");
        Map<String, Object> idCardMap = (Map<String, Object>) list.get(0);

        Map<String, Object> idCard = (Map<String, Object>) idCardMap.get("data");

        return idCard;

    }

    /**
     * 识别 银行卡 信息
     *
     * @param bankCardResult
     * @return
     */
    public static Map<String, Object> getBankCardInfo(String bankCardResult) {

        Map<String, Object> bankCardMap = JSON.parseObject(bankCardResult);

        bankCardMap = (Map<String, Object>) bankCardMap.get("data");

        List<Map<String, Object>> list = (List<Map<String, Object>>) bankCardMap.get("items");
        Map<String, Object> bankCardMaps = new LinkedHashMap<>();

        for (Map<String, Object> map : list) {
            bankCardMaps.put(map.get("item").toString(), map.get("itemstring"));
        }
        return bankCardMaps;
    }

    public static List<String> getText(String textResult) {
        Map<String, Object> bankCardMap = JSON.parseObject(textResult);

        bankCardMap = (Map<String, Object>) bankCardMap.get("data");

        List<Map<String, Object>> list = (List<Map<String, Object>>) bankCardMap.get("items");

        List<String> stringList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            stringList.add(map.get("itemstring").toString());
        }
        return stringList;
    }



}
