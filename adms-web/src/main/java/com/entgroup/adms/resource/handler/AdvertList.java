package com.entgroup.adms.resource.handler;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entgroup.adms.model.system.Ad;
import com.entgroup.adms.model.system.Video;
import com.entgroup.adms.model.system.VideoPlayRecord;
import com.entgroup.adms.resource.handler.base.BaseHandler;
import com.entgroup.adms.service.VideoPlayRecordService;
import com.entgroup.adms.service.VideoService;
import com.entgroup.adms.service.impl.VideoPlayRecordServiceImpl;
import com.entgroup.adms.util.CheckInput;
import com.entgroup.adms.util.CheckReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Description: TODO(获得广告列表)
 * @Author: sunzhen
 * @Date: 2017/3/15
 */

@Component("handler_advertList_v3.0.1.0")
public class AdvertList extends BaseHandler {

    @Autowired
    protected VideoPlayRecordService videoPlayRecordService;

    @Override
    public void process(JSONObject input, JSONObject output, int clientType, String clientIP) {

        CheckInput checkInput = new CheckInput(input);

        Long videoId = null;
        System.out.println(input);
        //进行视频播放的数据统计
        //public VideoPlayRecord(Long videoId, String sourceId, String deviceId, Date playTime, Integer devicePlatform, String ip, String regionCode, String cityCode, String countyCode, String browser, String deviceType, String pn, Integer net, String osv, String bn, Integer sw, Integer sh, String sdkVersion, Long platformId) {

        VideoPlayRecord videoPlayRecord = new VideoPlayRecord(input.getLong("programId"), input.getString("programId"), input.getString("imei"), new Date(), input.getInteger("client"), clientIP, "regionCode", "cityCode", input.getString("deviceType"), input.getInteger("net"), input.getString("osv"), input.getString("bn"), input.getInteger("sw"), input.getInteger("sh"), input.getString("sdkVersion"), input.getLong("platformId"));

        try {
            videoPlayRecordService.insert(videoPlayRecord);
        }catch (Exception e) {
            log.error(e.toString());
        }

        CheckReturn programId = checkInput.checkKey(CheckInput.KeySelect.require, CheckInput.KeyType.integer, "programId");
        if (!programId.isSuccess()) {
            programId.wrong(output);
            return;
        } else if (programId.isUserable()) {
            Video video = new Video();
            video.setSourceId(input.getString("programId"));
            video = videoService.selectOne(new EntityWrapper<Video>(video));
            if (video != null) {
                videoId = video.getId();
            } else {
                output.put("errCode", 1);
                output.put("msg", "未找到对应的videoId");
                return;
            }
        }

        /*CheckReturn platformIdCheck = checkInput.checkKey(CheckInput.KeySelect.require, CheckInput.KeyType.integer, "platformId");
        if (!platformIdCheck.isSuccess()) {
            platformIdCheck.wrong(output);
            return;
        } else if (platformIdCheck.isUserable()) {
            platformId = input.getLongValue("platformId");
        }*/

        //查询广告列表
        List<Ad> adList = adService.selectAdList(videoId);
        for (Ad ad : adList) {
            ad.setStyleId(8l);
            String imagePath = "http://101.200.207.204:9080/adResources/" + ad.getImagePath();
            ad.setImagePath(imagePath);
        }
        JSONObject js = new JSONObject();
        js.put("advert", adList);
        output.put("data", js);
        output.put("errCode", 0);
        output.put("msg", "查询成功");
    }
}
