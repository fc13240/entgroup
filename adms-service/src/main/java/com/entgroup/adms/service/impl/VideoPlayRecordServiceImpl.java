package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entgroup.adms.model.system.VideoPlayRecord ;
import com.entgroup.adms.mapper.VideoPlayRecordMapper ;
import com.entgroup.adms.model.system.VideoPlayRecordNote;
import com.entgroup.adms.service.VideoPlayRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ShiXinPeng
 * @since 2017-06-08
 */
@Service
public class VideoPlayRecordServiceImpl extends ServiceImpl<VideoPlayRecordMapper, VideoPlayRecord> implements VideoPlayRecordService {

    @Override
    public List<VideoPlayRecordNote> getCurrentDayPlayAllCount() {
        System.err.println("开始执行定时任务");
        Date dNow = new Date();   //当前时间
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
        Date dBefore = calendar.getTime();   //得到前一天的时间

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式

        String defaultStartDate = sdf.format(dBefore);    //格式化前一天

        String startString = defaultStartDate + " 00:00:00";
        String endString = defaultStartDate + " 23:59:59";
//        String startString = "2017-06-07 00:00:00";
//        String endString = "2017-06-07 23:59:59";

        List<VideoPlayRecord> videoPlayRecordList = baseMapper.selectList(new EntityWrapper<VideoPlayRecord>().between("play_time", startString, endString));

        if (videoPlayRecordList.size()>0){
            LinkedHashMap<Long, VideoPlayRecordNote> resultMap = new LinkedHashMap<>();
            Date date = new Date();
            for (VideoPlayRecord videoPlayRecord : videoPlayRecordList) {
                long videoId = videoPlayRecord.getVideoId();

                if (resultMap.containsKey(videoId)){
//                    System.out.println("如果已有该视频则保存到该条数据中去"+videoId);
                    //如果已有该视频则保存到该条数据中去
                    VideoPlayRecordNote videoPlayRecordNote = resultMap.get(videoId);
                    videoPlayRecordNote.setPlayRecordCount(videoPlayRecordNote.getPlayRecordCount()+1);
                    //添加记录时间
                    videoPlayRecordNote.setTimes(getTimesWithPlayTime(videoPlayRecord.getPlayTime(),false,videoPlayRecordNote.getTimes()));
                    resultMap.put(videoId,videoPlayRecordNote);
                }else {
//                    System.out.println("如果没有该视频则保存到该条数据中去"+videoId);
                    //如果没有这个已有数据则装填数据
                    VideoPlayRecordNote note = new VideoPlayRecordNote();
                    note.setVideoId(videoId);
                    note.setPlayRecordCount(1);
                    note.setEntryTime(date);
                    String times = getTimesWithPlayTime(videoPlayRecord.getPlayTime(),true,null);
                    note.setTimes(times);
                    resultMap.put(videoId,note);
                }

            }
            //获取到数据进行排序然后
            System.err.println("resultMap"+resultMap.size());
            Set<Map.Entry<Long, VideoPlayRecordNote>> entrySet = resultMap.entrySet();
            LinkedList<VideoPlayRecordNote> noteLinkedList = new LinkedList<VideoPlayRecordNote>();
            for (Map.Entry<Long, VideoPlayRecordNote> entry : entrySet) {
                noteLinkedList.add(entry.getValue());
            }
            if (noteLinkedList.size()<=1000){
                return noteLinkedList;
            }else {
                return noteLinkedList.subList(0, 1000);
            }



        }
        return null;
    }
    /**
     * @Author: ShiXin Peng
     * @MethodName: getTimesWithPlayTime
     * @Description: 把date格式的字符串生成24位数的记录字符串
     * @date 6/13/17
     */
    private String getTimesWithPlayTime(Date timeDate,boolean isNew,String timesArrayStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeDate);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (isNew){//最新的一条数据
            ArrayList<Integer> timesArrayList = new ArrayList();
            for (int i = 0;i<24;i++){
                timesArrayList.add(i,0);
            }
            timesArrayList.set(hour,1);

            StringBuffer stringBuffer = new StringBuffer();
            for (Integer integer : timesArrayList) {
                stringBuffer.append(integer+",");
            }
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
            return stringBuffer.toString();

        }else {
            String[] strings = timesArrayStr.split(",");
            ArrayList<Integer> timesArray = new ArrayList<>();
            for (int i = 0;i<strings.length;i++) {
                timesArray.add(i,Integer.parseInt(strings[i]));
            }

            int hourCount = timesArray.get(hour)+1;
            timesArray.set(hour,hourCount);
            StringBuffer stringBuffer = new StringBuffer();
            for (Integer integer : timesArray) {
                stringBuffer.append(integer+",");
            }
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
            return stringBuffer.toString();
        }

    }
}
