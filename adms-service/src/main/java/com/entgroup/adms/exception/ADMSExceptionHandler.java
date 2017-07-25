package com.entgroup.adms.exception;

import com.alibaba.fastjson.JSON;
import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.util.BeanValidators;
import com.entgroup.adms.util.MediaTypes;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author mengqch
 * @ClassName: ADMSExceptionHandler
 * @Description: 异常集中处理
 */
@ControllerAdvice
public class ADMSExceptionHandler extends ResponseEntityExceptionHandler {

    private transient final Logger logger = LoggerFactory
            .getLogger(ADMSExceptionHandler.class);

    @ExceptionHandler(ADMSException.class)
    public void handleException(ADMSException ex, HttpServletRequest request,
                                HttpServletResponse response) {
        // String errcode = ((GTRXException) ex).getErrcode();
        logger.error("发生WEB异常", ex);
        logger.error("发生WEB异常" + ex.getMessage());

        // 判断请求的类型，如果是ajax，则返回标准的错误ajax异常，否则重定向到错误处理页面
        // FIXME ajax登陆超时处理
        boolean isajaxreq = StringUtils.equalsIgnoreCase("XMLHttpRequest",
                "X-Requested-With");
        try {

            if (isajaxreq) {
                JsonResult result = new JsonResult();
                result.setStatus("500");
                result.setSuccess(false);

                if (ex instanceof ADMSException) {
                    result.setMsg(ex.getMessage());
                } else {
                    result.setMsg("系统错误，请与管理员联系。");
                }

                String s = "";
                byte[] content = s.getBytes();
                try {
                    s = JSON.toJSONString(result);
                    content = s.getBytes(CharEncoding.UTF_8);
                } catch (Exception e) {
                    logger.warn("转换json对象错误", e);
                }
                response.setCharacterEncoding(CharEncoding.UTF_8);
                response.setContentType(MediaTypes.JSON_UTF_8);
                response.getOutputStream().write(content);

            } else {
                // 传送错误提示信息
                if (ex instanceof ADMSException) { // ADMSException 显示错误代码和错误描述
                    request.setAttribute("errormessage", ex.getMessage());
                }
                request.setAttribute("errobj", ex);
                request.getRequestDispatcher("/error").forward(request,
                        response);
            }

        } catch (Exception e) {
            logger.warn("系统错误", e);
        }

    }

    /**
     * 处理JSR311 Validation异常.
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public final ResponseEntity<?> handleException(
            ConstraintViolationException ex, WebRequest request) {
        JsonResult result = new JsonResult();
        result.setStatus("500");
        result.setSuccess(false);
        List<String> errors = BeanValidators.extractMessage(ex
                .getConstraintViolations());
        result.setMsg(errors.get(0));// 默认只放置第一个 错误消息
        String body = JSON.toJSONString(result);
        if (logger.isDebugEnabled()) {
            logger.debug("服务器数据验证失败:[{}]", body);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType
                .parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
        return handleExceptionInternal(ex, body, headers, HttpStatus.OK,
                request);
    }

    /**
     * 处理Validate IllegalArgumentException
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public final ResponseEntity<?> handleException(IllegalArgumentException ex,
                                                   WebRequest request) {
        JsonResult result = new JsonResult();
        result.setStatus("500");
        result.setSuccess(false);
        result.setMsg(ex.getMessage());
        String body = JSON.toJSONString(result);
        if (logger.isDebugEnabled()) {
            logger.debug("服务器数据验证失败:[{}]", body);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType
                .parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
        return handleExceptionInternal(ex, body, headers, HttpStatus.OK,
                request);
    }

}
