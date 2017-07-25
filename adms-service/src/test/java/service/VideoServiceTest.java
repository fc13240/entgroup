package service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.Video;
import com.entgroup.adms.service.VideoService;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @description 用途说明
 * Video: hpb
 * Date: 2017/2/28
 */

public class VideoServiceTest extends BaseServiceTest<VideoService>{

    /**
     * @throws IOException
     */
    @Test
    public void testPage() throws IOException {
        EntityWrapper<Video> entityWrapper = new EntityWrapper<>(new Video());
        entityWrapper.like("login_name", "12")//column为数据库字段
                .orderBy("id desc")
                .between("id", "1", "10");
        List<Video> VideoList = service.selectList(entityWrapper);
        System.err.println("no page:" + VideoList.size());
        Page<Video> page = new Page<Video>(1, 5);
        page = service.selectPage(page, entityWrapper);
        System.err.println(" page 1:" + page.toString());
        page = new Page<Video>(2, 5);
        page = service.selectPage(page, entityWrapper);
        System.err.println(" page 2:" + page.toString());


//        entityWrapper.where("login_name={0}", "'123'").and("id=1")
////                .groupBy("x1").groupBy("x2,x3")
////                .having("x1=11").having("x3=433")
//                .orderBy("id").orderBy("d1,d2");

    }
    /**
     * void
     *
     * @title getMatchVideoList
     * @description TODO test
     * @throws IOException
     * @author mxy
     * @date 2017-03-20 16:14
     * @modifier
     * @remark
     * @version V1.0
     */
    @Test
    public void getMatch4VideoList() throws IOException {
//        EntityWrapper<Video> entityWrapper = new EntityWrapper<>(new Video());
//        entityWrapper.like("name", "测试");//colum数据库字段
//        Video Video = service.selectOne(entityWrapper);
//        System.out.println(Video);
        Page<Video> videoList = new Page<Video>(1, 10);
        videoList.setOrderByField("created");
        videoList.setAsc(false);
        videoList = service.getMatch4VideoList(videoList,"测试",0,1L,2L);
        System.out.println(videoList.getRecords());
    }

    /**
     * void
     *
     * @title getAds4VideoList
     * @description TODO Test
     * @author mxy
     * @date 2017-03-21 15:11
     * @modifier
     * @remark
     * @version V1.0
     * @throws IOException
     */
    @Test
    public void getAds4VideoList() throws IOException {
        Page<Video> videoList = new Page<Video>(1, 10);
        videoList.setOrderByField("created");
        videoList.setAsc(false);
        videoList = service.getAds4VideoList(videoList,null,0,1L,2L);
        System.out.println(videoList.getRecords());
    }
}

