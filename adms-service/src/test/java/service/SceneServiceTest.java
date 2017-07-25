package service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entgroup.adms.model.TreeNode;
import com.entgroup.adms.model.system.AdSlot;
import com.entgroup.adms.model.system.Scene;
import com.entgroup.adms.service.AdSlotService;
import com.entgroup.adms.service.SceneService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.IOException;
import java.util.List;
/**
 * @author mxy
 * @ClassName: SceneServiceTest
 * @Description: 
 * @date 2017-03-27 16:05
 */
public class SceneServiceTest extends BaseServiceTest<SceneService>{
    
    /**
     * void
     *
     * @title getMatchSceneList
     * @description TODO test
     * @throws IOException
     * @author mxy
     * @date 2017-03-20 16:14
     * @modifier
     * @remark
     * @version V1.0
     */
    @Test
    public void getScene4AdsList() throws IOException {
        List<Scene> SceneList = service.getScene4AdsList(0L,null,null);
        System.out.println(SceneList);
    }
    protected AdSlotService adSlotService;
    /**
     * void
     *
     * @title getSceneList4TreeNode
     * @description TODO
     * @author mxy
     * @date 2017-03-22 15:27
     * @modifier
     * @remark
     * @version V1.0
     * @throws IOException
     */
    @Test
    public void getSceneList4TreeNode() throws IOException {
        ApplicationContext aCtx = new FileSystemXmlApplicationContext(
                "classpath:testContext.xml");
        adSlotService = aCtx.getBean(AdSlotService.class);
        //查询当前广告位已有标签
        AdSlot adSlot = adSlotService.selectById(103L);
        // may be null
        String[] sceneIds = null;

        if (null != adSlot) {
            sceneIds = adSlot.getSceneIds() == null ? null : adSlot.getSceneIds().split(",");
        }
        //     查询所有的场景,并封装成treeNode对象,并把当前角色已有的权限选中
        List <Scene> scenes = service.selectList(new EntityWrapper<Scene>());
        List<TreeNode> treeNodes = Lists.newArrayList();
        for (Scene scene : scenes) {
            Boolean mark = Boolean.FALSE;
            if (ArrayUtils.indexOf(sceneIds, scene.getId()+"") > -1) {
                mark = Boolean.TRUE;
            }
            treeNodes.add(new TreeNode(scene.getId().intValue(),scene.getParentId().intValue(),scene.getName(),mark));
        }
        System.out.println(treeNodes);

    }
}

