package com.prostate.sms.controller;

import com.github.qcloudsms.httpclient.HTTPException;
import com.prostate.common.base.BaseController;
import com.prostate.redis.RedisSerive;
import com.prostate.sms.entity.AmountView;
import com.prostate.sms.entity.ParamTimeView;
import com.prostate.sms.entity.PhoneNumberView;
import com.prostate.sms.entity.SmsParams;
import com.prostate.sms.service.SmsService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping(value = "sms")
public class SmsController extends BaseController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisSerive redisSerive;
    /**
     * 发送 付款成功提示
     *
     * @return
     * @throws HTTPException
     * @throws IOException
     * @templateId 161858
     * @templateParam 付款成功时间
     */
    @PostMapping(value = "sendPaymentSuccess")
    public Map<String, Object> sendPaymentSuccess(@Validated({ParamTimeView.class}) SmsParams smsParams)  throws HTTPException, IOException {
        ArrayList<String> params = new ArrayList<>();
        params.add(smsParams.getParamTime());

        boolean b = smsService.singleSendByTemplate("86", smsParams.getPhoneNumber(), 161858, params);
        if (b) {
            return smsSendSuccess("发送成功");
        }
        return smsSendFailed("发送失败");
    }

    /**
     * 问诊结束发给患者
     *
     * @return
     * @throws HTTPException
     * @throws IOException
     * @templateId 162053
     */
    @PostMapping(value = "sendInquiryEndToPatient")
    public Map<String, Object> sendInquiryEndToPatient(@Validated({PhoneNumberView.class}) SmsParams smsParams) throws HTTPException, IOException {
        ArrayList<String> params = new ArrayList<>();

        boolean b = smsService.singleSendByTemplate("86", smsParams.getPhoneNumber(), 162053, params);
        if (b) {
            return smsSendSuccess("发送成功");
        }
        return smsSendFailed("发送失败");
    }

    /**
     * 问诊退款
     *
     * @return
     * @throws HTTPException
     * @throws IOException
     * @templateId 162054
     */
    @PostMapping(value = "sendInquiryRefund")
    public Map<String, Object> sendInquiryRefund(@Validated({PhoneNumberView.class}) SmsParams smsParams) throws HTTPException, IOException {
        ArrayList<String> params = new ArrayList<>();

        boolean b = smsService.singleSendByTemplate("86", smsParams.getPhoneNumber(), 162054, params);

        if (b) {
            return smsSendSuccess("发送成功");
        }
        return smsSendFailed("发送失败");
    }


    /**
     * 短信登录提示
     *
     * @return
     * @throws HTTPException
     * @throws IOException
     * @templateId 162056
     * @templateParam 验证码
     * @templateParam 有效时间
     */
    @PostMapping(value = "sendLoginCode")
    public Map<String, Object> sendLoginCode(@Validated({PhoneNumberView.class}) SmsParams smsParams) throws HTTPException, IOException {

        //生成6位数字验证码
        String numberCode = RandomStringUtils.randomNumeric(6);

        //添加模版参数
        ArrayList<String> params = new ArrayList<>();
        params.add(numberCode);
        params.add("5");



        //调用短信模版单发服务发送短信
        boolean b = smsService.singleSendByTemplate("86", smsParams.getPhoneNumber(), 162055, params);

        //发送结果判断
        if (b) {
            //验证信息添加到redis缓存中
            redisSerive.insert(numberCode, smsParams.getPhoneNumber(), 305);
            return smsSendSuccess("发送成功");
        }
        return smsSendFailed("发送失败");
    }


    /**
     * 登录页密码重置短信提示
     *
     * @return
     * @throws HTTPException
     * @throws IOException
     * @templateId 162057
     * @templateParam 验证码
     * @templateParam 有效时间
     */
    @PostMapping(value = "sendPasswordReplaceCode")
    public Map<String, Object> sendPasswordReplaceCode(@Validated({PhoneNumberView.class}) SmsParams smsParams) throws HTTPException, IOException {

        //生成6位数字验证码
        String numberCode = RandomStringUtils.randomNumeric(6);

        //添加模版参数
        ArrayList<String> params = new ArrayList<>();
        params.add(numberCode);
        params.add("5");



        boolean b = smsService.singleSendByTemplate("86", smsParams.getPhoneNumber(), 162056, params);

        //发送结果判断
        if (b) {
            //验证信息添加到redis缓存中
            redisSerive.insert(numberCode, smsParams.getPhoneNumber(), 305);

            return smsSendSuccess("发送成功");
        }
        return smsSendFailed("发送失败");
    }

    /**
     * 注册短信提示
     *
     * @return
     * @throws HTTPException
     * @throws IOException
     * @templateId 162055
     * @templateParam 验证码
     * @templateParam 有效时间
     */
    @PostMapping(value = "sendRegisterCode")
    public Map<String, Object> sendRegisterCode(@Validated({PhoneNumberView.class}) SmsParams smsParams) throws HTTPException, IOException {
        //生成6位数字验证码
        String numberCode = RandomStringUtils.randomNumeric(6);

        //添加模版参数
        ArrayList<String> params = new ArrayList<>();
        params.add(numberCode);
        params.add("5");



        boolean b = smsService.singleSendByTemplate("86", smsParams.getPhoneNumber(), 162057, params);


        if (b) {
            //验证信息添加到redis缓存中
            redisSerive.insert(numberCode, smsParams.getPhoneNumber(), 305);

            return smsSendSuccess("发送成功");
        }
        return smsSendFailed("发送失败");
    }

    /**
     * 认证成功短信提示
     *
     * @return
     * @throws HTTPException
     * @throws IOException
     * @templateId 162058
     */
    @PostMapping(value = "sendProveSuccess")
    public Map<String, Object> sendProveSuccess(@Validated({PhoneNumberView.class}) SmsParams smsParams) throws HTTPException, IOException {
        ArrayList<String> params = new ArrayList<>();

        boolean b = smsService.singleSendByTemplate("86", smsParams.getPhoneNumber(), 162058, params);
        if (b) {
            return smsSendSuccess("发送成功");
        }
        return smsSendFailed("发送失败");
    }

    /**
     * 认证失败短信提示
     *
     * @return
     * @throws HTTPException
     * @throws IOException
     * @templateId 162059
     */
    @PostMapping(value = "sendProveFailed")
    public Map<String, Object> sendProveFailed(@Validated({PhoneNumberView.class}) SmsParams smsParams) throws HTTPException, IOException {
        ArrayList<String> params = new ArrayList<>();

        boolean b = smsService.singleSendByTemplate("86", smsParams.getPhoneNumber(), 162059, params);
        if (b) {
            return smsSendSuccess("发送成功");
        }
        return smsSendFailed("发送失败");
    }

    /**
     * 问诊结束提醒
     *
     * @return
     * @throws HTTPException
     * @throws IOException
     * @templateId 162061
     */
    @PostMapping(value = "sendInquiryEndToDoctor")
    public Map<String, Object> sendInquiryEndToDoctor(@Validated({PhoneNumberView.class}) SmsParams smsParams) throws HTTPException, IOException {
        ArrayList<String> params = new ArrayList<>();

        boolean b = smsService.singleSendByTemplate("86", smsParams.getPhoneNumber(), 162061, params);
        if (b) {
            return smsSendSuccess("发送成功");
        }
        return smsSendFailed("发送失败");
    }


    /**
     * 提现短信提醒
     *
     * @return
     * @throws HTTPException
     * @throws IOException
     * @templateId 162062
     * @templateParam 提现申请时间
     * @templateParam 金额
     */
    @PostMapping(value = "sendBalanceForCash")
    public Map<String, Object> sendBalanceForCash(@Validated({AmountView.class}) SmsParams smsParams) throws HTTPException, IOException {

        ArrayList<String> params = new ArrayList<>();

        params.add(smsParams.getParamTime());
        params.add(smsParams.getAmount());

        boolean b = smsService.singleSendByTemplate("86", smsParams.getPhoneNumber(), 162062, params);
        if (b) {
            return smsSendSuccess("发送成功");
        }
        return smsSendFailed("发送失败");
    }

    /**
     * 提醒填写问诊结论
     *
     * @return
     * @throws HTTPException
     * @throws IOException
     * @templateId 162063
     */
    @PostMapping(value = "sendTimeOut")
    public Map<String, Object> sendTimeOut(@Validated({PhoneNumberView.class}) SmsParams smsParams) throws HTTPException, IOException {

        ArrayList<String> params = new ArrayList<>();

        boolean b = smsService.singleSendByTemplate("86", smsParams.getPhoneNumber(), 162063, params);
        if (b) {
            return smsSendSuccess("发送成功");
        }
        return smsSendFailed("发送失败");
    }


}
