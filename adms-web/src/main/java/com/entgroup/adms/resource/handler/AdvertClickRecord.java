package com.entgroup.adms.resource.handler;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entgroup.adms.model.system.AdClickRecord;
import com.entgroup.adms.model.system.AdSlot;
import com.entgroup.adms.model.system.OrderAd;
import com.entgroup.adms.resource.handler.base.BaseHandler;
import com.entgroup.adms.util.CheckInput;
import com.entgroup.adms.util.CheckReturn;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: TODO(广告点击操作)
 * @Author: sunzhen
 * @Date: 2017/3/15
 */

@Component("handler_advertClickCount_v1.0")
public class AdvertClickRecord extends BaseHandler {


    @Override
    public void process(JSONObject input, JSONObject output, int clientType, String clientIP) {

        CheckInput checkInput = new CheckInput(input);

        Long adId = null;
        Long slotId = null;
        String deviceType = null;
        String deviceId = null;

        CheckReturn adIdCheck = checkInput.checkKey(CheckInput.KeySelect.require, CheckInput.KeyType.integer, "advertId");
        if (!adIdCheck.isSuccess()) {
            adIdCheck.wrong(output);
            return;
        } else if (adIdCheck.isUserable()) {
            adId = input.getLongValue("advertId");
        }

        CheckReturn slotIdCheck = checkInput.checkKey(CheckInput.KeySelect.require, CheckInput.KeyType.integer, "slotId");
        if (!slotIdCheck.isSuccess()) {
            slotIdCheck.wrong(output);
            return;
        } else if (slotIdCheck.isUserable()) {
            slotId = input.getLongValue("slotId");
        }

        CheckReturn deviceTypeCheck = checkInput.checkKey(CheckInput.KeySelect.require, CheckInput.KeyType.string, "deviceType");
        if (!deviceTypeCheck.isSuccess()) {
            deviceTypeCheck.wrong(output);
            return;
        } else if (deviceTypeCheck.isUserable()) {
            deviceType = input.getString("deviceType");
        }

        CheckReturn deviceIdCheck = checkInput.checkKey(CheckInput.KeySelect.require, CheckInput.KeyType.string, "imei");
        if (!deviceIdCheck.isSuccess()) {
            deviceIdCheck.wrong(output);
            return;
        } else if (deviceIdCheck.isUserable()) {
            deviceId = input.getString("imei");
        }

        //根据ad_id查询order_id
        OrderAd orderAd = new OrderAd();
        orderAd.setAdId(adId);
        orderAd.setDeleted(0);
        orderAd = orderAdService.selectOne(new EntityWrapper<>(orderAd));
        String orderId = orderAd.getOrderId();
        //根据slot_id查询program_id
        AdSlot adSlot = adSlotService.selectById(slotId);
        Long videoId = adSlot.getVideoId();
        Long programId = videoService.selectById(videoId).getProgramId();

        AdClickRecord adClickRecord = new AdClickRecord();
        adClickRecord.setSlotId(slotId);
        adClickRecord.setAdId(adId);
        adClickRecord.setProgramId(programId);
        adClickRecord.setClickTime(new Date());
        adClickRecord.setDevicePlatform(clientType);
        adClickRecord.setIp(clientIP);
        adClickRecord.setDeviceId(deviceId);
        adClickRecord.setDeviceType(deviceType);
        adClickRecord.setOrderId(orderId);
        adClickRecordService.insert(adClickRecord);

        output.put(ERROR_CODE, 0);
        output.put(MESSAGE, "广告展示页面点击统计成功");

    }
}
