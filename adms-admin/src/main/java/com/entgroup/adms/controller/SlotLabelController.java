package com.entgroup.adms.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.model.TreeNode;
import com.entgroup.adms.model.system.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author mxy
 * @ClassName: SlotLabelController
 * @Description: 广告位标签管理
 * @date 2017-03-22 11:45
 */
@Controller
@RequestMapping("/slotLabel")
public class SlotLabelController extends BaseController {

    /**
     * 系统模块名
     */
    private static final String SYSTEM_MODULE = "广告位标签管理";

    /**
     *
     * @param adSlotId
     * @return JsonResult
     *
     * @title initSlotLabel4ZTree
     * @description TODO 初始化标签菜单树
     * @author mxy
     * @date 2017-03-22 11:46
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping("/initSlotLabel")
    @ResponseBody
    @SystemControllerLog(moduleName = { SYSTEM_MODULE }, description = "初始化标签菜单树")
    public JsonResult initSlotLabel4ZTree(@RequestParam Long adSlotId) {
        log.info("initSlotLabel......");
        //查询当前广告位已有标签
        AdSlot adSlot = adSlotService.selectById(adSlotId);
        // may be null
        String[] sceneIds = null;
        String[] personIds = null;
        String[] objectIds = null;
        String[] slotLabelIds = null;
        List <Long> parentIds = Lists.newArrayList();
        Boolean mark;//是否打开
        Boolean mark2;//是否选择
        if (null != adSlot) {
            sceneIds = adSlot.getSceneIds()==null?null:adSlot.getSceneIds().split(",");
            personIds = adSlot.getPersonIds()==null?null:adSlot.getPersonIds().split(",");
            objectIds = adSlot.getObjectIds()==null?null:adSlot.getObjectIds().split(",");
            slotLabelIds = adSlot.getSlotLabelIds()==null?null:adSlot.getSlotLabelIds().split(",");
        }

        //查询所有的场景,并封装成treeNode对象,并把当前广告位已有的场景选中
        List<TreeNode> treeNodes = Lists.newArrayList();
        List <Scene> scenes = sceneService.selectList(new EntityWrapper<Scene>().ne("parent_id",1L));
        mark = Boolean.FALSE;
        for (Scene scene : scenes) {
            mark2 = Boolean.FALSE;
            if (ArrayUtils.indexOf(sceneIds, scene.getId()+"") > -1) {
                mark2 = Boolean.TRUE;
                parentIds.add(scene.getParentId());
            }
            treeNodes.add(new TreeNode(scene.getId().intValue(),scene.getParentId().intValue(),scene.getName(),mark2));
        }
        scenes = sceneService.selectList(new EntityWrapper<Scene>().eq("parent_id",1L));
        for (Scene scene : scenes) {
            mark2 = Boolean.FALSE;
            if (parentIds.contains(scene.getId())||ArrayUtils.indexOf(sceneIds, scene.getId()+"") > -1) {
                mark2 = Boolean.TRUE;
                mark = Boolean.TRUE;
            }
            treeNodes.add(new TreeNode(scene.getId().intValue(),scene.getParentId().intValue(),scene.getName(),mark2));
        }
        treeNodes.add(new TreeNode(1,0,"场景",mark,mark));//场景总标签

        //查询所有的明星,并封装成treeNode对象,并把当前广告位已有的明星选中
        List <Person> persons = personService.selectList(new EntityWrapper<Person>());
        mark = Boolean.FALSE;
        for (Person person : persons) {
            mark2 = Boolean.FALSE;
            if (ArrayUtils.indexOf(personIds, person.getId()+"") > -1) {
                mark2 = Boolean.TRUE;
                mark = Boolean.TRUE;
            }
            treeNodes.add(new TreeNode(person.getId().intValue(),2,person.getName(),mark2));
        }
        treeNodes.add(new TreeNode(2,0,"明星",mark,mark));//明星总标签

        //查询所有的物品,并封装成treeNode对象,并把当前广告位已有的物品选中
        List <RecognitionObject> objects = objectService.selectList(new EntityWrapper<RecognitionObject>().ne
                ("parent_id",3L));
        parentIds = Lists.newArrayList();
        for (RecognitionObject object : objects) {
            mark2 = Boolean.FALSE;
            if (ArrayUtils.indexOf(objectIds, object.getId()+"") > -1) {
                mark2 = Boolean.TRUE;
                parentIds.add(object.getParentId());
            }
            treeNodes.add(new TreeNode(object.getId().intValue(),object.getParentId().intValue(),object.getName(),mark2));
        }
        objects = objectService.selectList(new EntityWrapper<RecognitionObject>().eq("parent_id",3L));
        for (RecognitionObject object : objects) {
            mark2 = Boolean.FALSE;
            if (parentIds.contains(object.getId())||ArrayUtils.indexOf(objectIds, object.getId()+"") > -1) {
                mark2 = Boolean.TRUE;
                mark = Boolean.TRUE;
            }
            treeNodes.add(new TreeNode(object.getId().intValue(),object.getParentId().intValue(),object.getName(),mark2));
        }
        treeNodes.add(new TreeNode(3,0,"物品",mark,mark));//物品总标签

        //查询所有的标签,并封装成treeNode对象,并把当前广告位已有的标签选中
        List <SlotLabel> slotLabels = slotLabelService.selectList(new EntityWrapper<SlotLabel>().ne("parent_id",4L));
        parentIds = Lists.newArrayList();
        for (SlotLabel slotLabel : slotLabels) {
            mark2 = Boolean.FALSE;
            if (ArrayUtils.indexOf(slotLabelIds, slotLabel.getId()+"") > -1) {
                mark2 = Boolean.TRUE;
                parentIds.add(slotLabel.getParentId());
            }
            treeNodes.add(new TreeNode(slotLabel.getId().intValue(),slotLabel.getParentId().intValue(),slotLabel.getName(),mark2));
        }
        slotLabels = slotLabelService.selectList(new EntityWrapper<SlotLabel>().eq("parent_id",4L));
        for (SlotLabel slotLabel : slotLabels) {
            mark2 = Boolean.FALSE;
            if (parentIds.contains(slotLabel.getId())||ArrayUtils.indexOf(slotLabelIds, slotLabel.getId()+"") > -1) {
                mark2 = Boolean.TRUE;
                mark = Boolean.TRUE;
            }
            treeNodes.add(new TreeNode(slotLabel.getId().intValue(),slotLabel.getParentId().intValue(),slotLabel.getName(),mark2));
        }
        treeNodes.add(new TreeNode(4,0,"其他",mark,mark));//其他总标签


        if (log.isDebugEnabled()) {
            log.debug("initSlotLabel......");
            log.debug("sceneIds:{[]}", sceneIds==null?null:sceneIds.toString());
            log.debug("personIds:{[]}", personIds==null?null:personIds.toString());
            log.debug("objectIds:{[]}", objectIds==null?null:objectIds.toString());
            log.debug("slotLabelIds:{[]}", slotLabelIds==null?null:slotLabelIds.toString());
        }
        jr = renderSuccess();
        jr.setData("sceneIds", sceneIds);
        jr.setData("personIds", personIds);
        jr.setData("objectIds", objectIds);
        jr.setData("slotLabelIds", slotLabelIds);
        jr.setData("treeNodes", treeNodes);
        return jr;
    }
}