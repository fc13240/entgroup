package service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.SceneRecognitionResult;
import com.entgroup.adms.service.SceneRecognitionResultService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author mxy
 * @ClassName: SceneRecognitionResultServiceTest
 * @Description: 
 * @date 2017-03-23 09:15
 */
public class SceneRecognitionResultServiceTest extends BaseServiceTest<SceneRecognitionResultService>{

    /**
     * @throws IOException
     */
    @Test
    public void testPage() throws IOException {
        EntityWrapper<SceneRecognitionResult> entityWrapper = new EntityWrapper<>(new SceneRecognitionResult());
        entityWrapper.like("login_name", "12")//column为数据库字段
                .orderBy("id desc")
                .between("id", "1", "10");
        List<SceneRecognitionResult> SceneRecognitionResultList = service.selectList(entityWrapper);
        System.err.println("no page:" + SceneRecognitionResultList.size());
        Page<SceneRecognitionResult> page = new Page<SceneRecognitionResult>(1, 5);
        page = service.selectPage(page, entityWrapper);
        System.err.println(" page 1:" + page.toString());
        page = new Page<SceneRecognitionResult>(2, 5);
        page = service.selectPage(page, entityWrapper);
        System.err.println(" page 2:" + page.toString());

    }
    /**
     * void
     *
     * @title getMatchSceneRecognitionResultList
     * @description TODO test
     * @throws IOException
     * @author mxy
     * @date 2017-03-20 16:14
     * @modifier
     * @remark
     * @version V1.0
     */
    @Test
    public void getMatchSceneRecognitionResultList() throws IOException {
        List<SceneRecognitionResult> SceneRecognitionResultList = service.getSceneRecognitionResultList
                (0L,null);
        System.out.println(SceneRecognitionResultList);
    }

    /**
     * @param
     * @return void
     * @throws IOException
     *
     * @title SortSRR
     * @description TODO 表中时间点排序
     * @author mxy
     * @date 2017-04-25 11:38
     * @modifier
     * @remark
     * @version V1.0
     */
    @Test
    public void SortSRR() throws IOException {
        List<SceneRecognitionResult> SceneRecognitionResultList = service.selectList(
                new EntityWrapper<SceneRecognitionResult>().like("video_position", "%,%"));
        List<SceneRecognitionResult> updateSRRList = Lists.newArrayList();
        String videoPosition;
        String updateVideoPosition;
        String[] temp;
        ArrayList<Integer> videoPositions;
        for (SceneRecognitionResult sceneRecognitionResult : SceneRecognitionResultList) {
            videoPositions = Lists.newArrayList();
            videoPosition = sceneRecognitionResult.getVideoPosition();
            temp=videoPosition.split(",");
            for (String s : temp) {
                videoPositions.add(Integer.parseInt(s));
            }
            Collections.sort(videoPositions);
            updateVideoPosition="";
            for (Integer position : videoPositions) {
                updateVideoPosition+=position+",";
            }
            updateVideoPosition = StringUtils.removeEnd(updateVideoPosition,",");
            sceneRecognitionResult.setVideoPosition(updateVideoPosition);
            if (!videoPosition.equals(updateVideoPosition)) {
                updateSRRList.add(sceneRecognitionResult);
            }
        }
        //System.out.println(updateSRRList.size());
        service.updateBatchById(updateSRRList);
    }
}

