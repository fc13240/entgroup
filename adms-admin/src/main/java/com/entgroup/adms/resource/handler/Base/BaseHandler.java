package com.entgroup.adms.resource.handler.Base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseHandler implements HandlerInterface {

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
