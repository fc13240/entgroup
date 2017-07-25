package com.entgroup.adms.resource.schedule;

import com.alibaba.fastjson.JSON;
import com.entgroup.adms.model.system.AdDisplayRecord;
import com.entgroup.adms.service.AdDisplayRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * redis批量转存储mysql定时任务
 *
 * @description 定时转存曝光记录缓存队列数据至数据库
 * User: hpb
 * Date: 2017/1/9
 */
@Component
public class RedisQueueScheduler {
    public static final String PREF_KEY_DISPLAY_RECORD = "key_display_record_queue";
    public static final String PREF_KEY_PLAY_RECORD = "key_play_record_queue";


    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    AdDisplayRecordService adDisplayRecordService;


    @Scheduled(initialDelay = 10000, fixedRate = 1800000)
    public void batchImportToMysql() {
        logger.info("start redis--->msyql task");
        ArrayList<AdDisplayRecord> displayRecordList = getList(PREF_KEY_DISPLAY_RECORD, AdDisplayRecord.class);
        if (displayRecordList != null && displayRecordList.size() > 0) {
            logger.info(" insert display record,   redis--->msyql ,size={}", displayRecordList.size());
            adDisplayRecordService.insertBatch(displayRecordList);
        }


    }

    private ArrayList getList(String key, Class cls) {
        Object bResult = redisTemplate.opsForList().rightPop(key);
        ArrayList list = new ArrayList();
        while (bResult != null) {
            list.add(JSON.parseObject(bResult.toString(), cls));
            bResult = redisTemplate.opsForList().rightPop(key);
        }
        return list;
    }

}
