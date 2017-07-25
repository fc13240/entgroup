package com.entgroup.adms.service;

import com.entgroup.adms.model.system.VideoPlayRecord;
import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.model.system.VideoPlayRecordNote;

import java.util.List;

/**
 * @Author ShiXin Peng
 * @ClassName: VideoPlayRecordService
 * @Description: 视频播放统计的service层
 * @date 6/8/17
 */
public interface VideoPlayRecordService extends IService<VideoPlayRecord>
{

    /**
     * @Author: ShiXin Peng
     * @MethodName: getCurrentDayPlayAllCount
     * @Description: 获取当天的播放总量
     * @date 6/8/17
     */
      List<VideoPlayRecordNote> getCurrentDayPlayAllCount();

}
