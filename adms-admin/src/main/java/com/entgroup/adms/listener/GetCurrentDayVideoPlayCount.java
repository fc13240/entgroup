package com.entgroup.adms.listener;


import com.entgroup.adms.model.system.VideoPlayRecordNote;
import com.entgroup.adms.service.VideoPlayRecordNoteService;
import com.entgroup.adms.service.VideoPlayRecordService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author ShiXin Peng
 * @ClassName: GetCurrentDayVideoPlayCount
 * @Description: 获取视频播放量的统计数据
 * @date 6/8/17
 */
public class GetCurrentDayVideoPlayCount {

    @Resource
    protected VideoPlayRecordService videoPlayCountService;
    @Resource
    protected VideoPlayRecordNoteService videoPlayRecordNoteService;
    /**
     * @Author: ShiXin Peng
     * @MethodName: executeVideoPlayCountTest
     * @Description: 定时任务的-播放量的统计测试方法
     * @date 6/8/17
     */
    public  void executeVideoPlayCountTest() {

        List<VideoPlayRecordNote> noteList = videoPlayCountService.getCurrentDayPlayAllCount();

        if (noteList!=null) {
            boolean insertBatch = videoPlayRecordNoteService.insertBatch(noteList);
            System.err.println("VideoPlayRecordNote-result="+insertBatch);
        }
    }
}
