package com.prostate.common.base;


import java.util.LinkedHashMap;
import java.util.Map;

public class BaseController {

    public Map<String, Object> resultMap;

    /**
     * 参数为空返回值
     *
     * @return
     */
    public Map emptyParamResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20001");
        resultMap.put("msg", "EMPTY_PARAM");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * 请求成功返回值
     *
     * @param result
     * @return
     */
    public Map querySuccessResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 分页查询请求成功返回值
     *
     * @param result
     * @return
     */
    public Map querySuccessResponse(Object result, String count) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "SUCCESS");
        resultMap.put("result", result);
        resultMap.put("count", count);
        return resultMap;
    }

    /**
     * 查询请求结果为空返回值
     *
     * @return
     */
    public Map queryEmptyResponse() {
        resultMap = new LinkedHashMap<>();

        resultMap.put("code", "40004");
        resultMap.put("msg", "RESULT_EMPTY");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * 请求失败返回值
     *
     * @return
     */
    public Map failedResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50002");
        resultMap.put("msg", "FAILED_EMPTY");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * 请求失败返回值
     *
     * @return
     */
    public Map insertFailedResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50003");
        resultMap.put("msg", "INSERT_FAILED");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * 插入请求成功返回值
     *
     * @return
     */
    public Map insertSuccseeResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "INSERT_SUCCESS");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * 插入请求成功返回值
     *
     * @return
     */

    public Map insertSuccseeResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "INSERT_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * UPDATE成功返回值
     *
     * @return
     */
    public Map updateSuccseeResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "UPDATE_SUCCESS");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * UPDATE成功返回值
     *
     * @param result
     * @return
     */
    public Map updateSuccseeResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "UPDATE_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * UPDATE请求失败返回值
     *
     * @return
     */
    public Map updateFailedResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50004");
        resultMap.put("msg", "UPDATE_FAILED");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * DELETE成功返回值
     *
     * @return
     */
    public Map deleteSuccseeResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "DELETE_SUCCESS");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * UPLOAD请求失败返回值
     *
     * @return
     */
    public Map upLoadFailedResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50005");
        resultMap.put("msg", "UPLOAD_FAILED");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * UPLOAD请求成功返回值
     *
     * @return
     */
    public Map upLoadSuccessResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "UPLOAD_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 短信发送成功
     *
     * @param result
     * @return
     */
    public Map smsSendSuccess(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "SEND_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 短信发送失败
     *
     * @param result
     * @return
     */
    public Map smsSendFailed(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "5012");
        resultMap.put("msg", "SEND_FAILED");
        resultMap.put("result", result);
        return resultMap;
    }
}
