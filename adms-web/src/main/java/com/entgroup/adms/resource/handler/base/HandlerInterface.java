/**
 * 
 */
package com.entgroup.adms.resource.handler.base;

import com.alibaba.fastjson.JSONObject;


public interface HandlerInterface {
    /**
     * 
     * @param input
     * @param output
     * @param clientType 客户端类型 1=Android, 2=iphone
     */
    public void process(JSONObject input, JSONObject output, int clientType, String clientIP);
}
