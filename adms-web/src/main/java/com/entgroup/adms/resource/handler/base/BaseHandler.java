package com.entgroup.adms.resource.handler.base;

import com.entgroup.adms.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

public abstract class BaseHandler implements HandlerInterface {

    //edited by mxy on 2017-03-27 16:29 begin
    @Autowired
    protected AdClickRecordService adClickRecordService;
    @Autowired
    protected OrderAdService orderAdService;
    @Autowired
    protected AdSlotService adSlotService;
    @Autowired
    protected VideoService videoService;
    @Autowired
    protected AdService adService;
    @Autowired
    protected CompanyService companyService;
    @Autowired
    protected SimpleCacheManager cacheManager;
    @Autowired
    protected RedisTemplate redisTemplate;
    @Autowired
    protected AdDisplayRecordService adDisplayRecordService;
    //edited by mxy on 2017-03-27 16:30 end

    protected static final String ERROR_CODE = "errCode";
    protected static final String MESSAGE = "msg";
    protected String appKey;
    protected String appPackageName;
    protected int sdkVersion;

    public int getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(int sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    protected String clientIP;
    protected String regionCode;
    protected String cityCode;

    protected Logger log = LoggerFactory.getLogger(getClass());

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppPackageName() {
        return appPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }



//    protected void setAddressInfo() {
//        if (StringUtils.isNotEmpty(clientIP)) {
//            try {
//                IPAddressInfoDTO addressInfo = new IPAddressUtils().getAddressFromBaidu(clientIP);
//                log.info("addressInfo:[{}]", addressInfo);
//                if (null != addressInfo) {
//                    //	取到省份
//                    if (null != addressInfo.getRegion()) {
//                        regionCode = regionService.getCodeForRegion(addressInfo.getRegion());
//                    }
//                    if (null != addressInfo.getCity()) {
//                        cityCode = regionService.getCodeForRegion(addressInfo.getCity());
//                    }
//
//                } else {
//                    regionCode = "110000";
//                    cityCode = "110000";
//
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }


}
