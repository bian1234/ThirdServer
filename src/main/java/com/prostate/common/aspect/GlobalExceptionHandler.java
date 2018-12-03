/**
 * 全局异常捕获
 */
package com.prostate.common.aspect;


import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    public Map<String, Object> resultMap;

    /**
     * 所有异常报错
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> allExceptionHandler(HttpServletRequest request, Exception exception) {

        log.error("我报错了：{}", exception.getLocalizedMessage());
        log.error("我报错了：{}", exception.getCause());
//        log.error("我报错了：{}",exception.getSuppressed());
        log.error("我报错了：{}", exception.getMessage());
//        log.error("我报错了：{}",exception.getStackTrace());

        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50000");
        resultMap.put("msg", "SYSTEM_ERROR");
        resultMap.put("result", "服务器异常!");
        return resultMap;
    }

    /**
     * Hibernate Validator 参数异常捕获
     *
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BindException.class)
    public Map<String, Object> bindExceptionHandler(BindException exception) throws Exception {
        resultMap = new LinkedHashMap<>();

        resultMap.put("code", "40001");
        resultMap.put("msg", "FAILED_PARAM");
        resultMap.put("result", fieldErrorsBuilder(exception.getFieldErrors()));

        return resultMap;
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public Map<String, Object> constraintViolationExceptionHandler(ConstraintViolationException exception) throws Exception {
        resultMap = new LinkedHashMap<>();

        resultMap.put("code", "40001");
        resultMap.put("msg", "FAILED_PARAM");
        resultMap.put("result", fieldErrorsBuilder(exception.getConstraintViolations()));

        return resultMap;
    }


    private static Map<String, Object> fieldErrorsBuilder(List<FieldError> fieldErrorList) {
        Map<String, Object> fieldErrorsMap = new LinkedHashMap<>();
        for (FieldError fieldError : fieldErrorList) {
            fieldErrorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return fieldErrorsMap;
    }

    private static Map<String, Object> fieldErrorsBuilder(Set<ConstraintViolation<?>> constraintViolationSet) {
        Map<String, Object> fieldErrorsMap = new LinkedHashMap<>();
        for (ConstraintViolation<?> constraintViolation : constraintViolationSet) {
            String paramName = constraintViolation.getPropertyPath().toString();
            paramName = paramName.substring(paramName.lastIndexOf(".") + 1);
            fieldErrorsMap.put(paramName, constraintViolation.getMessage());
        }
        return fieldErrorsMap;
    }
}
