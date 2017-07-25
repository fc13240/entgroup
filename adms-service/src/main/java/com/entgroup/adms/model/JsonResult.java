package com.entgroup.adms.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 与前台交互的JSON实体.
 * @Author: yanleichang.
 * @Create: 2014年6月23日.
 */
public class JsonResult {

    private Map<String, Object> data;// 传到前台的数据
    private String url;// 需要跳转的URI
    private String msg;// 失败的消息
    private String status;//状态码：500,404,200.....
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    private boolean success;// true-成功,false- 失败
    private boolean validateFlag; // 前台校验

    public JsonResult() {
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(String key, Object value) {
        if (null == this.data) {
            this.data = new HashMap<String, Object>();
        }
        this.data.put(key, value);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String errormsg) {
        this.msg = errormsg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isValidateFlag() {
        return validateFlag;
    }

    public void setValidateFlag(boolean validateFlag) {
        this.validateFlag = validateFlag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
