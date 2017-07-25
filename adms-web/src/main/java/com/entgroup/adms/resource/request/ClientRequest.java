package com.entgroup.adms.resource.request;

import com.alibaba.fastjson.JSONObject;
import com.entgroup.adms.model.system.Company;
import com.entgroup.adms.service.CompanyService;
import com.entgroup.adms.util.HttpUtil;
import com.entgroup.adms.util.StringUtils;
import com.entgroup.adms.exception.ADMSException;
import com.entgroup.adms.resource.handler.base.BaseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

@RestController
public class ClientRequest extends AbstractController {
    @Autowired
    protected ServletContext servletContext;
    protected Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    HttpServletRequest request;
    @Resource
    private CompanyService companyService;
    @Resource
    private SimpleCacheManager cacheManager;

    @RequestMapping(value = "/clientRequest", produces = "text/html;charset=UTF-8", method = RequestMethod.POST, headers = {"content-type=application/json;charset=UTF-8"})
    @ResponseBody
    public String clientRequest(@RequestBody JSONObject input) {

        String clientIP = null;
        try {
            clientIP = HttpUtil.getClientIp(request);
            log.info("clientRequest......clientIP:[{}]", clientIP);
        } catch (ADMSException e1) {
            e1.printStackTrace();
        }
        JSONObject result = new JSONObject();
        String key = input.getString("appKey");
        if (StringUtils.isEmpty(key)) {
            key = request.getHeader("appKey");
        }
        String appPackageName = input.getString("appPackageName");
        if (StringUtils.isEmpty(appPackageName)) {
            appPackageName = request.getHeader("appPackageName");
        }

        int client = input.getIntValue("client");

        if (StringUtils.isEmpty(key)) {

            result.put("errCode", 1);
            result.put("msg", "请提供授权key");
            return result.toJSONString();
        }
        if (client != 3 && StringUtils.isEmpty(appPackageName)) {
            result.put("errCode", 1);
            result.put("msg", "请提供应用包名");
            return result.toJSONString();
        }

        if (!input.containsKey("platformId") || input.getIntValue("platformId") < 1) {
            Cache appCache = cacheManager.getCache("appCache");
            String platformId = appCache.get("platformId_" + key + "_" + appPackageName, String.class);
            if (platformId != null) {
                input.put("platformId", platformId);
            } else {
                Company company = companyService.selectByAppkeyAndPackageName(key, appPackageName);
                if (company == null) {
                    result.put("errCode", 1);
                    result.put("msg", "不存在授权key及包名键值对");
                    return result.toJSONString();
                }else {
                    input.put("platformId",company.getId());
                }
            }
        }

        String method = input.getString("method");

        if (StringUtils.isEmpty(method)) {
            result.put("errCode", 1);
            result.put("msg", "method参数为空");
            return result.toJSONString();
        }
        String version = input.getString("version").trim();

        try {
            BaseHandler handler = null;
            String handlerName = "handler_" + method + "_v" + version;
            log.debug(handlerName);
            handler = (BaseHandler) WebApplicationContextUtils
                    .getRequiredWebApplicationContext(servletContext).getBean(
                            handlerName);
            if (handler == null) {
                result.put("errCode", 1);
                result.put("msg", "不存在相关处理器");
                return result.toJSONString();
            }
            if (input.containsKey("sdkVersion")) {
                String sdkVersion = input.getString("sdkVersion");
                try {
                    int sdkv = Integer.parseInt(sdkVersion.replace(".", ""));
                    handler.setSdkVersion(sdkv);
                } catch (Exception e) {
                }

            }
            handler.setAppKey(key);
            handler.setAppPackageName(appPackageName);
            handler.setClientIP(clientIP);
            handler.process(input, result, client, clientIP);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            result.put("errCode", 1);
            result.put("msg", "相关处理器处理异常");
        }

        log.info(result.toString());
        return result.toJSONString();
    }


    private String geneSignature(String appKey, String appPackageName) {
        String string = "appKey=" + appKey + "&appPackageName=" + appPackageName
                + "&xiaobaishiji=v1";
        String signature = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("MD5");
            crypt.reset();
            crypt.update(string.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return signature;
    }

    private String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.handleRequest(request, response);
    }
}

