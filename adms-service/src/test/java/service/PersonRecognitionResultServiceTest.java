package service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.PersonRecognitionResult;
import com.entgroup.adms.service.PersonRecognitionResultService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author mxy
 * @ClassName: PersonRecognitionResultServiceTest
 * @Description: 
 * @date 2017-03-23 09:22
 */
public class PersonRecognitionResultServiceTest extends BaseServiceTest<PersonRecognitionResultService>{

    /**
     * @throws IOException
     */
    @Test
    public void testPage() throws IOException {
        EntityWrapper<PersonRecognitionResult> entityWrapper = new EntityWrapper<>(new PersonRecognitionResult());
        entityWrapper.like("login_name", "12")//column为数据库字段
                .orderBy("id desc")
                .between("id", "1", "10");
        List<PersonRecognitionResult> PersonRecognitionResultList = service.selectList(entityWrapper);
        System.err.println("no page:" + PersonRecognitionResultList.size());
        Page<PersonRecognitionResult> page = new Page<PersonRecognitionResult>(1, 5);
        page = service.selectPage(page, entityWrapper);
        System.err.println(" page 1:" + page.toString());
        page = new Page<PersonRecognitionResult>(2, 5);
        page = service.selectPage(page, entityWrapper);
        System.err.println(" page 2:" + page.toString());

    }
    /**
     * void
     *
     * @title getMatchPersonRecognitionResultList
     * @description TODO test
     * @throws IOException
     * @author mxy
     * @date 2017-03-20 16:14
     * @modifier
     * @remark
     * @version V1.0
     */
    @Test
    public void getMatchPersonRecognitionResultList() throws IOException {
        List<PersonRecognitionResult> PersonRecognitionResultList = service.getPersonRecognitionResultList
                (0L,null);
        System.out.println(PersonRecognitionResultList);
    }

    /**
     * @param
     * @return void
     * @throws IOException
     *
     * @title update
     * @description TODO
     * @author mxy
     * @date 2017-04-01 10:05
     * @modifier
     * @remark
     * @version V1.0
     */
    @Test
    public void update() throws IOException {
        PersonRecognitionResult personRecognitionResult = new PersonRecognitionResult();
        personRecognitionResult.setImageServer("1");
        String[] ids = {"1","2","3","4","5"};
        Boolean result = service.update(personRecognitionResult,new EntityWrapper<PersonRecognitionResult>().in("id",ids));
        System.out.println(result);
    }
    
    /**
     * @param
     * @return void
     * @throws IOException
     *
     * @title SortPRR
     * @description TODO 表中时间点排序
     * @author mxy
     * @date 2017-04-25 11:38
     * @modifier
     * @remark
     * @version V1.0
     */
    @Test
    public void SortPRR() throws IOException {
        List<PersonRecognitionResult> PersonRecognitionResultList = service.selectList(
                new EntityWrapper<PersonRecognitionResult>().like("video_position", "%,%"));
        List<PersonRecognitionResult> updatePRRList = Lists.newArrayList();
        String videoPosition;
        String updateVideoPosition;
        String[] temp;
        ArrayList<Integer> videoPositions;
        for (PersonRecognitionResult personRecognitionResult : PersonRecognitionResultList) {
            videoPositions = Lists.newArrayList();
            videoPosition = personRecognitionResult.getVideoPosition();
            temp=videoPosition.split(",");
            for (String s : temp) {
                videoPositions.add(Integer.parseInt(s));
            }
            Collections.sort(videoPositions);
            updateVideoPosition="";
            for (Integer position : videoPositions) {
                updateVideoPosition+=position+",";
            }
            updateVideoPosition = StringUtils.removeEnd(updateVideoPosition, ",");
            personRecognitionResult.setVideoPosition(updateVideoPosition);
            if (!videoPosition.equals(updateVideoPosition)) {
                updatePRRList.add(personRecognitionResult);
            }
        }
        //System.out.println(updatePRRList.size());
        service.updateBatchById(updatePRRList);
    }
}

