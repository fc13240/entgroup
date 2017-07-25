/**
 * 
 */
package com.entgroup.adms.resource.handler.Base;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletContext;


public interface HandlerInterface {
    /**
     * 
     * @param input
     * @param output
     * @param clientType 客户端类型 1=Android, 2=iphone
     */
    public void process(JSONObject input, JSONObject output, Integer clientType, ServletContext servletContext);
}
