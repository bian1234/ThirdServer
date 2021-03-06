package com.prostate.sms.service;


import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;


@Service
public interface SmsService {

    /**
     * 指定模板单发短信
     */
    boolean singleSendByTemplate(String nationCode, String phoneNumber, int templateId, ArrayList<String> param) throws HTTPException, IOException;

    /**
     * 指定模板群发短信
     */
    boolean multiSendByTemplate(String nationCode, ArrayList<String> phoneNumbers, int templateId, ArrayList<String> params) throws HTTPException, IOException;

    /**
     * 单发短信
     */
    boolean singleSend(int type, String nationCode, String phoneNumber, String msg) throws HTTPException, IOException;

    /**
     * 群发短信
     */
    boolean multiSend(int type, String nationCode, ArrayList<String> phoneNumbers, ArrayList<String> params, String msg) throws HTTPException, IOException;

}
