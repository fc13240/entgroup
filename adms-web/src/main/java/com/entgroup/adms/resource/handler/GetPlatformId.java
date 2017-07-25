package com.entgroup.adms.resource.handler;

import com.alibaba.fastjson.JSONObject;
import com.entgroup.adms.model.system.Company;
import com.entgroup.adms.resource.handler.base.BaseHandler;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO(获得平台id)
 * @Author: sunzhen
 * @Date: 2017/3/15
 */

@Component("handler_getPlatformId_v1.0")
public class GetPlatformId extends BaseHandler {

    @Override
    public void process(JSONObject input, JSONObject output, int clientType, String clientIP) {

        Cache appCache = cacheManager.getCache("appCache");

        Long platformId;
        String cacheKey = "platformId_" + appKey + "_" + appPackageName;
        String cachePlatformId = appCache.get(cacheKey, String.class);
        if (cachePlatformId != null) {
            log.info("read platformId from cache");
            platformId = Long.parseLong(cachePlatformId);
        } else {
            Company company = companyService.selectByAppkeyAndPackageName(getAppKey(), getAppPackageName());
            if (company == null) {
                output.put(ERROR_CODE, 1);
                output.put(MESSAGE, "暂无此合作伙伴");
                return;
            }
            platformId = company.getId();
            appCache.put(cacheKey, platformId.toString());
        }

        JSONObject js = new JSONObject();
        js.put("platformId", platformId);
        output.put("data", js);
        output.put(ERROR_CODE, 0);
        output.put(MESSAGE, "平台id获取成功");

    }
}
