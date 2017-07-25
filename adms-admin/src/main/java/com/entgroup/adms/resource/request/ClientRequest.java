package com.entgroup.adms.resource.request;

import com.alibaba.fastjson.JSONObject;

import com.entgroup.adms.controller.BaseController;
import com.entgroup.adms.resource.handler.Base.BaseHandler;
import com.entgroup.adms.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author: sunzhen
 * @Date: 2017/3/30
 */
@RestController
public class ClientRequest extends BaseController {
    @RequestMapping(value = "/clientRequest", produces = "application/json;charset=UTF-8", method = RequestMethod.POST, headers = {"content-type=application/json;charset=UTF-8"})
    @ResponseBody
    public String clientRequest(@RequestBody JSONObject input) {

        log.info("request input : [{}]", input.toString());

        JSONObject result = new JSONObject();
        try {
            String method = input.getString("method").trim();
            if (StringUtils.isEmpty(method)) {
                result.put("errorCode", 1);
                result.put("msg", "method参数为空");
                return result.toString();
            }
            BaseHandler handler = null;
            String handlerName = "handler_" + method;
            handler = (BaseHandler) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean(handlerName);
            if (handler == null) {
                result.put("errorCode", 1);
                result.put("msg", "不存在相关的处理器");
                return result.toString();
            }
            handler.process(input, result, null, servletContext);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            result.put("errCode", 1);
            result.put("msg", "相关处理器处理异常");
        }
        log.info("result : [{}]", result.toString());
        return result.toJSONString();
    }

}
