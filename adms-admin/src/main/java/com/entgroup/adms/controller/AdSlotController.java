package com.entgroup.adms.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.conf.AuthorityConstants;
import com.entgroup.adms.dto.*;
import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.model.system.*;
import com.entgroup.adms.util.DateUtils;
import com.entgroup.adms.util.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author mxy
 * @ClassName: AdSlotController
 * @Description: 广告位管理
 * @date 2017-03-22 11:45
 */
@Controller
@RequestMapping("/adSlot")
public class AdSlotController extends BaseController {

    /**
     * 系统模块名
     */
    static final String SYSTEM_MODULE = "广告位管理";

    /**
     * @param
     *
     * @return String
     *
     * @throws @author
     *         mxy
     * @title portal
     * @description TODO 筛选广告位列表页
     * @date 2017-03-16 13:59
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/screenAdSlot")
    @RequiresPermissions(value = {AuthorityConstants.AdSlot.VIEW_ADSLOT_SCREENADSLOT}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "筛选广告位列表页")
    public String screenAdSlotList(
            Model model, @ModelAttribute ScreenAdSlotDTO screenAdSlotDTO,
            @RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize
    ) {
        log.info("screenAdSlot......");

        // 视频内容平台列表
        List<Company> companyList = companyService.getContentPlatformList();
        // 视频分类列表
        List<ProgramType> programTypeList = programTypeService.selectList(null);

        // 获取视频（列表）识别信息
        Page<Video> videoPage = new Page(pageNum, pageSize);
        videoPage.setOrderByField("id");
        videoPage.setAsc(true);
        videoPage = videoService.getAds4VideoList(videoPage, string2Null(screenAdSlotDTO.getVideoName()),
                                                    screenAdSlotDTO.getStatusSelect(), screenAdSlotDTO.getCompanyId(),
                                                    screenAdSlotDTO.getTypeId());
        String videoIds = "";
        for (Video video : videoPage.getRecords()) {
            videoIds += video.getId() + ",";
        }
        videoIds = StringUtils.removeEnd(string2Null(videoIds), ",");
        List<Video> videoList = videoService.getMatchByVideoIds(videoIds);

        // 封装DTO
        List<ScreenAdSlotDTO> dtoList = Lists.newArrayList();
        int i = 0;
        for (Video video : videoPage.getRecords()) {
            ScreenAdSlotDTO dto = new ScreenAdSlotDTO();
            dto.setVideoId(video.getId());
            dto.setCreated(DateUtils.format(video.getCreated()));
            dto.setVideoPlatform(video.getCompany().getShortName());
            dto.setVideoName(video.getName());
            dto.setVideoType(video.getProgramType().getName());
/*            for (ProgramType programType : programTypeList) {
                if (programType.getId().equals(video.getProgramType().getId())) {
                    dto.setVideoType(programType.getName());
                    break;
                }
            }*/
            String imagesNum = getArrayNum(video.getAds()) + "/" + (getArrayNum(videoList.get(i).getSrr()) +
                    getArrayNum(videoList.get(i).getPrr()));
            dto.setImagesNum(imagesNum);
            String status = video.getStatusSelect() == 0 ? "待筛选" : "已筛选";
            dto.setStatus(status);
            dto.setStatusSelect(video.getStatusSelect());
            dtoList.add(dto);
            i++;
        }

        PageInfo<Video> page = new PageInfo(videoPage);
        model.addAttribute("page", page);
        model.addAttribute("dtoList", dtoList);
        model.addAttribute("companyList", companyList);
        model.addAttribute("programTypeList", programTypeList);
        return "adSlot/screenAdSlot";
    }

    /**
     * @param model
     * @param videoId
     *
     * @return String
     *
     * @title screenAdSlotImg
     * @description TODO 筛选广告位画面
     * @author mxy
     * @date 2017-03-26 15:51
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/screenAdSlotImg")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "筛选广告位画面")
    public String screenAdSlotImg(Model model, @RequestParam("videoId") Long videoId) {
        log.info("screenAdSlotImg......");

        // 获取单个视频广告位信息
        List<AdSlot> adSlotList = adSlotService.selectList(new EntityWrapper<AdSlot>().eq("video_id", videoId));

        // 获取单个视频的场景识别信息
        List<SceneRecognitionResult> srrlist = sceneRecognitionResultService.getSceneRecognitionResultList(videoId,
                                                                                                           null);
        List<Scene> sceneList = Lists.newArrayList();
        // 封装SceneDTO
        for (SceneRecognitionResult sceneRecognitionResult : srrlist) {
            List<AdSlotDTO> DTOList = Lists.newArrayList();
            String[] videoPositions = sceneRecognitionResult.getVideoPosition().split(",");
            for (String videoPosition : videoPositions) {
                AdSlotDTO dto = new AdSlotDTO();
                dto.setCreated(DateUtils.format(sceneRecognitionResult.getCreated()));
                dto.setVideoId(videoId);
                dto.setVideoName(sceneRecognitionResult.getVideo().getName());
                dto.setVideoPosition(videoPosition);
                dto.setVideoPositionTime(second2Time(Integer.parseInt(videoPosition)));
                for (AdSlot adSlot : adSlotList) {
                    if (adSlot.getVideoPosition().equals(
                            Integer.parseInt(videoPosition)) && adSlot.getSceneIds().matches(
                            ".*\\b" + sceneRecognitionResult.getScene().getId() + "\\b.*")) {//PersonIds包含时添加
                        dto.setStatus(adSlot.getAdId() == 0L ? 1 : 2);
                        break;
                    }
                }
                String imageServer = sceneRecognitionResult.getImageServer();
                dto.setImageServer(imageServer==null ? "null" : imageServer);
                String imgPathPrefix;
                if (null != imageServer) {
                    imageServer = imageServer.substring(
                            imageServer.lastIndexOf(".") + 1, imageServer.length());
                    imgPathPrefix = "http://api.xiaobaishiji.com:81/image/"
                            + imageServer + "/" + videoId + "/";
                } else {
                    imgPathPrefix = "http://101.200.207.204:8080/thumbnail/" + videoId
                            + "/";
                }
                Integer timeIn = (Integer.parseInt(videoPosition) - 0) >= 0 ? (Integer
                        .parseInt(videoPosition) - 0) : 1;//由于抽帧全部减0秒
                dto.setPictureAddress(imgPathPrefix + timeIn + ".jpg");//由于抽帧全部减0秒
                DTOList.add(dto);
            }
            Scene scene = sceneRecognitionResult.getScene();
            scene.setAdSlotDTOList(DTOList);
            sceneList.add(scene);
        }
        // 获取单个视频的明星识别信息
        List<PersonRecognitionResult> prrlist = personRecognitionResultService.getPersonRecognitionResultList(videoId,
                                                                                                              null);
        List<Person> personList = Lists.newArrayList();
        // 封装PersonDTO
        for (PersonRecognitionResult personRecognitionResult : prrlist) {
            List<AdSlotDTO> DTOList = Lists.newArrayList();
            String[] videoPositions = personRecognitionResult.getVideoPosition().split(",");
            for (String videoPosition : videoPositions) {
                AdSlotDTO dto = new AdSlotDTO();
                dto.setCreated(DateUtils.format(personRecognitionResult.getCreated()));
                dto.setVideoId(videoId);
                dto.setVideoName(personRecognitionResult.getVideo().getName());
                dto.setVideoPosition(videoPosition);
                dto.setVideoPositionTime(second2Time(Integer.parseInt(videoPosition)));
                for (AdSlot adSlot : adSlotList) {
                    if (adSlot.getVideoPosition().equals(
                            Integer.parseInt(videoPosition)) && adSlot.getPersonIds().matches(
                            ".*\\b" + personRecognitionResult.getPerson().getId() + "\\b.*")) {//PersonIds包含时添加
                        dto.setStatus(adSlot.getAdId() == 0L ? 1 : 2);
                        break;
                    }
                }
                String imageServer = personRecognitionResult.getImageServer();
                dto.setImageServer(imageServer==null ? "null" : imageServer);
                String imgPathPrefix;
                if (null != imageServer) {
                    imageServer = imageServer.substring(
                            imageServer.lastIndexOf(".") + 1, imageServer.length());
                    imgPathPrefix = "http://api.xiaobaishiji.com:81/image/"
                            + imageServer + "/" + videoId + "/";
                } else {
                    imgPathPrefix = "http://101.200.207.204:8080/thumbnail/" + videoId
                            + "/";
                }
                Integer timeIn = (Integer.parseInt(videoPosition) - 0) >= 0 ? (Integer
                        .parseInt(videoPosition) - 0) : 1;//由于抽帧全部减0秒
                dto.setPictureAddress(imgPathPrefix + timeIn + ".jpg");//由于抽帧全部减0秒
                DTOList.add(dto);

            }
            Person person = personRecognitionResult.getPerson();
            person.setAdSlotDTOList(DTOList);
            personList.add(person);
        }
        // 获取单个视频信息
        Video video = videoService.selectById(videoId);
        model.addAttribute("video", video);
        model.addAttribute("sceneList", sceneList);
        model.addAttribute("personList", personList);
        return "adSlot/screenAdSlotImg";
    }

    /**
     * @param videoId
     * @param results
     *         选择结果集，格式：1-labelId-videoPosition,2-labelId-videoPosition,... 1是场景，2是明星
     * @param unchooseResults
     *         未选择结果集，格式：1-labelId-videoPosition,2-labelId-videoPosition,... 1是场景，2是明星
     *
     * @return JsonResult
     *
     * @title saveScreenAdSlotResults
     * @description TODO
     * @author mxy
     * @date 2017-03-30 16:03
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping("/saveScreenAdSlotResults")
    @ResponseBody
    @RequiresPermissions(value = {AuthorityConstants.AdSlot.PERMISSION_SCREENADSLOTIMG_SAVESCREENADSLOTRESULTS}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "保存广告位筛选结果")
    public JsonResult saveScreenAdSlotResults(
            @RequestParam Long videoId, @RequestParam(required = false) String results,
            @RequestParam(required = false) String unchooseResults
    ) {
        log.info("saveScreenAdSlotResults......");

        // 获取单个视频广告位信息
        List<AdSlot> adSlotList = adSlotService.selectList(new EntityWrapper<AdSlot>().eq("video_id", videoId));

        //封装选择结果集
        List<AdSlot> resultList = string2List4AdSlot(results, videoId);
        //封装未选择结果集
        List<AdSlot> unchooseResultList = string2List4AdSlot(unchooseResults, videoId);
        //预先处理已有但要删除的广告位
        if (!adSlotList.isEmpty()) {
            for (AdSlot adSlot : adSlotList) {
                if (adSlot.getAdId() == 0L) {//若广告位已有广告则直接跳过
                    for (AdSlot adSlot1 : unchooseResultList) {
                        if (adSlot.getVideoPosition().equals(adSlot1.getVideoPosition())) {//判断已有广告位时间点是否在结果集中
                            switch (adSlot1.getInitialLabel()) {//判断改时间点的标签是否对应
                                case "1":
                                    adSlot.setSceneIds(adSlot.getSceneIds().replace("," + adSlot1.getSceneIds(), ""));
                                    adSlot.setInitialLabel(
                                            adSlot.getInitialLabel().replace("," + adSlot1.getSceneIds(), ""));
                                    break;
                                case "2":
                                    adSlot.setPersonIds(
                                            adSlot.getPersonIds().replace("," + adSlot1.getPersonIds(), ""));
                                    adSlot.setInitialLabel(
                                            adSlot.getInitialLabel().replace("," + adSlot1.getPersonIds(), ""));
                                    break;
                                case "3":
                                    adSlot.setObjectIds(
                                            adSlot.getObjectIds().replace("," + adSlot1.getObjectIds(), ""));
                                    adSlot.setInitialLabel(
                                            adSlot.getInitialLabel().replace("," + adSlot1.getObjectIds(), ""));
                                    break;
                                default:
                                    break;
                            }
                            break;
                        }
                    }
                }
            }
        }
        //需要删除的广告位id集合
        List<Long> deleteIds = Lists.newArrayList();
        if (!adSlotList.isEmpty()) {
            for (AdSlot adSlot : adSlotList) {
                if (adSlot.getAdId() == 0L) {//若广告位已有广告则直接跳过
                    Boolean mark = Boolean.TRUE;
                    for (AdSlot adSlot1 : resultList) {
                        if (adSlot.getVideoPosition().equals(adSlot1.getVideoPosition())) {//判断已有广告位时间点是否在结果集中
                            mark = Boolean.FALSE;
                            break;
                        }
                    }
                    if (mark) {
                        deleteIds.add(adSlot.getId());
                    }
                }
            }
        }

        //需要更新的广告位集合
        List<AdSlot> updateList = Lists.newArrayList();

        //需要新增的广告位集合
        List<AdSlot> insertList = Lists.newArrayList();

        for (AdSlot adSlot : resultList) {
            Boolean mark = Boolean.TRUE;//区分是否添加入新增集合
            for (AdSlot adSlot1 : adSlotList) {
                Boolean mark1 = Boolean.TRUE;//区分是否添加入更新集合
                if (adSlot.getVideoPosition().equals(adSlot1.getVideoPosition())) {//判断时间点是否已有广告位
                    mark = Boolean.FALSE;
                    for (AdSlot adSlot2 : updateList) {
                        if (adSlot2.getVideoPosition().equals(adSlot.getVideoPosition())) {//判断时间点是否已在更新集合
                            mark1 = Boolean.FALSE;
                            if (!adSlot.getSceneIds().equals("0")) {//若不为空直接添加
                                adSlot2.setSceneIds(adSlot2.getSceneIds() + "," + adSlot.getSceneIds());
                                adSlot2.setInitialLabel(adSlot2.getInitialLabel() + "," + adSlot.getSceneIds());
                            } else if (!adSlot.getPersonIds().equals("0")) {//若不为空直接添加
                                adSlot2.setPersonIds(adSlot2.getPersonIds() + "," + adSlot.getPersonIds());
                                adSlot2.setInitialLabel(adSlot2.getInitialLabel() + "," + adSlot.getPersonIds());
                            } else if (!adSlot.getObjectIds().equals("0")) {//若不为空直接添加
                                adSlot2.setObjectIds(adSlot2.getObjectIds() + "," + adSlot.getObjectIds());
                                adSlot2.setInitialLabel(adSlot2.getInitialLabel() + "," + adSlot.getObjectIds());
                            }
                        }
                    }
                    if (mark1) {
                        switch (adSlot.getInitialLabel()) {
                            case "1":
                                if (!adSlot1.getSceneIds().matches(
                                        ".*\\b" + adSlot.getSceneIds() + "\\b.*")) {//原有不包含时添加
                                    adSlot1.setSceneIds(adSlot1.getSceneIds() + "," + adSlot.getSceneIds());
                                    adSlot1.setInitialLabel(adSlot1.getInitialLabel() + "," + adSlot.getSceneIds());
                                    updateList.add(adSlot1);
                                }
                                break;
                            case "2":
                                if (!adSlot1.getPersonIds().matches(
                                        ".*\\b" + adSlot.getPersonIds() + "\\b.*")) {//原有不包含时添加
                                    adSlot1.setPersonIds(adSlot1.getPersonIds() + "," + adSlot.getPersonIds());
                                    adSlot1.setInitialLabel(adSlot1.getInitialLabel() + "," + adSlot.getPersonIds());
                                    updateList.add(adSlot1);
                                }
                                break;
                            case "3":
                                if (!adSlot1.getObjectIds().matches(
                                        ".*\\b" + adSlot.getObjectIds() + "\\b.*")) {//原有不包含时添加
                                    adSlot1.setObjectIds(adSlot1.getObjectIds() + "," + adSlot.getObjectIds());
                                    adSlot1.setInitialLabel(adSlot1.getInitialLabel() + "," + adSlot.getObjectIds());
                                    updateList.add(adSlot1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                }
            }
            if (mark) {//添加新增列表
                for (AdSlot adSlot1 : insertList) {
                    if (adSlot.getVideoPosition().equals(adSlot1.getVideoPosition())) {//判断时间点是否已有，已有则添加到新增列表中已有元素中
                        mark = Boolean.FALSE;
                        switch (adSlot.getInitialLabel()) {
                            case "1":
                                adSlot1.setSceneIds((adSlot1.getSceneIds().equals(
                                        "0") ? "0," : adSlot1.getSceneIds() + ",") + adSlot.getSceneIds());
                                adSlot1.setInitialLabel(adSlot1.getInitialLabel() + "," + adSlot.getSceneIds());
                                break;
                            case "2":
                                adSlot1.setPersonIds((adSlot1.getPersonIds().equals(
                                        "0") ? "0," : adSlot1.getPersonIds() + ",") + adSlot.getPersonIds());
                                adSlot1.setInitialLabel(adSlot1.getInitialLabel() + "," + adSlot.getSceneIds());
                                break;
                            case "3":
                                adSlot1.setObjectIds((adSlot1.getObjectIds().equals(
                                        "0") ? "0," : adSlot1.getObjectIds() + ",") + adSlot.getObjectIds());
                                adSlot1.setInitialLabel(adSlot1.getInitialLabel() + "," + adSlot.getSceneIds());
                                break;
                            default:
                                break;
                        }
                        break;
                    }
                }
                if (mark) {//原有广告位集合中没有，新增列表中也没有则加入新增列表
                    switch (adSlot.getInitialLabel()) {
                        case "1":
                            adSlot.setSceneIds("0," + adSlot.getSceneIds());
                            adSlot.setInitialLabel("0," + adSlot.getSceneIds());
                            break;
                        case "2":
                            adSlot.setPersonIds("0," + adSlot.getPersonIds());
                            adSlot.setInitialLabel("0," + adSlot.getPersonIds());
                            break;
                        case "3":
                            adSlot.setObjectIds("0," + adSlot.getObjectIds());
                            adSlot.setInitialLabel("0," + adSlot.getObjectIds());
                            break;
                        default:
                            break;
                    }
                    insertList.add(adSlot);
                }
            }
        }

        try {
            if (!deleteIds.isEmpty()) {
                adSlotService.deleteBatchIds(deleteIds);
            }
            if (!updateList.isEmpty()) {
                adSlotService.updateBatchById(updateList);
            }
            if (!insertList.isEmpty()) {
                adSlotService.insertBatch(insertList);
            }
            jr = renderSuccess("提交成功");
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("提交失败");
        }
        return jr;
    }

    /**
     * @param results
     * @param videoId
     *
     * @return List<AdSlot>
     *
     * @throws
     * @title string2List4AdSlot
     * @description TODO 将results参数打包成list
     * @author mxy
     * @date 2017-03-31 11:24
     * @modifier
     * @remark
     * @version V1.0
     */
    private List<AdSlot> string2List4AdSlot(String results, Long videoId) {
        //结果集
        List<AdSlot> resultList = Lists.newArrayList();
        //封装结果集
        String[] resultX = null;
        if (null != results && !results.trim().equals("")) {
            resultX = results.split(",");
        }
        if (resultX != null) {
            for (String s : resultX) {
                String[] result = s.split("-");
                AdSlot adSlot = new AdSlot();
                adSlot.setVideoId(videoId);
                adSlot.setVideoPosition(Integer.parseInt(result[2]));
                adSlot.setCreated(new Date());
                adSlot.setInitialLabel(result[0]);//1-场景，2-明星，3物品
                adSlot.setImageServer(result[3].equals("null")?null:result[3]);
                switch (result[0]) {
                    case "1":
                        adSlot.setSceneIds(result[1]);
                        //adSlot.setInitialLabel(""+result[1]);
                        break;
                    case "2":
                        adSlot.setPersonIds(result[1]);
                        //adSlot.setInitialLabel(""+result[1]);
                        break;
                    case "3":
                        adSlot.setObjectIds(result[1]);
                        //adSlot.setInitialLabel(""+result[1]);
                        break;
                    default:
                        break;
                }
                resultList.add(adSlot);
            }
        }
        return resultList;
    }

    /**
     * @param videoId
     * @return JsonResult
     * @throws
     *
     * @title saveScreenAdSlotResults
     * @description TODO 提交广告位交筛选状态
     * @author mxy
     * @date 2017-04-15 15:25
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping("/saveScreenAdSlotStatus")
    @ResponseBody
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "提交广告位交筛选状态")
    public JsonResult saveScreenAdSlotStatus(
            @RequestParam Long videoId) {
        log.info("saveScreenAdSlotStatus......");
        try {
            Video video = videoService.selectById(videoId);
            video.setStatusSelect(2);
            videoService.updateById(video);
            jr = renderSuccess("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("保存失败");
        }
        return jr;
    }

    /**
     * @param model
     * @param adjustPriceDTO
     * @param pageNum
     * @param pageSize
     *
     * @return String
     *
     * @title adjustPrice
     * @description TODO 广告位调价列表页
     * @author mxy
     * @date 2017-03-26 17:16
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/adjustPrice")
    @RequiresPermissions(value = {AuthorityConstants.AdSlot.VIEW_ADSLOT_ADJUSTPRICE}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告位调价列表页")
    public String adjustPriceList(
            Model model, @ModelAttribute AdjustPriceDTO adjustPriceDTO,
            @RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize
    ) {
        log.info("adjustPrice......");
        // 视频内容平台列表
        List<Company> companyList = companyService.getContentPlatformList();
        // 视频分类列表
        List<ProgramType> programTypeList = programTypeService.selectList(null);
        // 获取节目价格信息列表
        Page<Program> programPage = new Page(pageNum, pageSize);
        programPage.setOrderByField("id");
        programPage.setAsc(false);
        programPage = programService.getProgramPriceList(programPage, string2Null(adjustPriceDTO.getProgramName()),
                                                         adjustPriceDTO.getCompanyId(), adjustPriceDTO.getTypeId(),
                                                         adjustPriceDTO.getLevelId());
        // 封装DTO
        List<AdjustPriceDTO> dtoList = Lists.newArrayList();

        for (Program program : programPage.getRecords()) {
            AdjustPriceDTO dto = new AdjustPriceDTO();
            dto.setProgramId(program.getId());
            dto.setCreated(DateUtils.format(program.getCreated()));
            dto.setProgramPlatform(program.getCompany() == null ? null : program.getCompany().getShortName());
            dto.setProgramName(program.getName());
            dto.setProgramType(program.getProgramType().getName());
            dto.setVideoNum(program.getVideoNum());
            dto.setAdSlotNum(program.getAdSlotNum());
            dto.setPrice(program.getProgramLevel().getName());
            // dto.setLevelId(program.getProgramLevel().getId());
            dtoList.add(dto);
        }

        EntityWrapper<Program> entityWrapper = new EntityWrapper<>(new Program());
        entityWrapper.like("name",
                           adjustPriceDTO.getProgramName() == null ? null :adjustPriceDTO.getProgramName());
        if (adjustPriceDTO.getCompanyId() != null) {
            entityWrapper.eq("company_id", adjustPriceDTO.getCompanyId());
        }
        if (adjustPriceDTO.getTypeId() != null) {
            entityWrapper.eq("type_id", adjustPriceDTO.getTypeId());
        }
        if (adjustPriceDTO.getLevelId() != null) {
            entityWrapper.eq("level_id", adjustPriceDTO.getLevelId());
        }
        entityWrapper.orderBy("id Asc");
        Page<Program> pageTemp = new Page(pageNum, pageSize);
        pageTemp = programService.selectPage(pageTemp, entityWrapper);

        PageInfo<Program> page = new PageInfo(pageTemp);
        model.addAttribute("page", page);
        model.addAttribute("dtoList", dtoList);
        model.addAttribute("companyList", companyList);
        model.addAttribute("programTypeList", programTypeList);
        return "adSlot/adjustPrice";
    }

    /**
     * @param newLevelId
     * @param programIds
     * @return JsonResult
     * @throws
     *
     * @title saveAdjustPrice
     * @description TODO 保存广告位调整价位结果
     * @author mxy
     * @date 2017-04-01 10:10
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping("/saveAdjustPrice")
    @ResponseBody
    @RequiresPermissions(value = {AuthorityConstants.AdSlot.PERMISSION_ADJUSTPRICE_SAVEADJUSTPRICE}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "保存广告位调整价位结果")
    public JsonResult saveAdjustPrice(
            @RequestParam(value = "newLevelId") Integer newLevelId, @RequestParam(value = "programIds") String
            programIds) {
        log.info("saveAdjustPrice......");
        if (integer2Null(newLevelId)==null) {//如果无操作则直接返回
            jr = renderSuccess();
            return jr;
        }
        String[] programIdX = ArrayUtils.removeElement(programIds.split(","),"");
        Program program = new Program();
        program.setLevelId(newLevelId);
        try {
            programService.update(program,new EntityWrapper<Program>().in("id",programIdX));
            jr = renderSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError(e.getMessage());
        }
        return jr;
    }
    /**
     * @param model
     * @param editLabelDTO
     * @param pageNum
     * @param pageSize
     *
     * @return String
     *
     * @title editLabelList
     * @description TODO 编辑广告位标签列表页
     * @author mxy
     * @date 2017-03-26 17:55
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/editLabel")
    @RequiresPermissions(value = {AuthorityConstants.AdSlot.VIEW_ADSLOT_EDITLABEL}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "编辑广告位标签列表页")
    public String editLabelList(
            Model model, @ModelAttribute EditLabelDTO editLabelDTO,
            @RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize
    ) {
        log.info("editLabel......");

        // 视频内容平台列表
        List<Company> companyList = companyService.getContentPlatformList();

        // 视频分类列表
        List<ProgramType> programTypeList = programTypeService.selectList(null);

        // 获取视频（列表）识别信息
        Page<Video> videoPage = new Page(pageNum, pageSize);
        videoPage.setOrderByField("id");
        videoPage.setAsc(true);
        videoPage = videoService.getAds4VideoList(videoPage, string2Null(editLabelDTO.getVideoName()),
                                                  editLabelDTO.getStatusSelect(), editLabelDTO.getCompanyId(),
                                                  editLabelDTO.getTypeId());
        //封装DTO
        List<EditLabelDTO> dtoList = Lists.newArrayList();

        for (Video video : videoPage.getRecords()) {
            EditLabelDTO dto = new EditLabelDTO();
            dto.setVideoId(video.getId());
            dto.setCreated(DateUtils.format(video.getCreated()));
            dto.setVideoPlatform(video.getCompany().getShortName());
            dto.setVideoName(video.getName());
            dto.setVideoType(video.getProgramType().getName());
            String labelNum = (getArrayNum(video.getAdsSids()) + getArrayNum(video.getAdsPids()) + getArrayNum(
                    video.getAdsOids()) + getArrayNum(video.getAdsLids()) - getArrayNum(
                    video.getAdsILs())) + "/" + getArrayNum(video.getAdsILs());
            dto.setLabelNum(labelNum);
            String status = video.getStatusSelect() == 0 ? "待筛选" : "已筛选";
            dto.setStatus(status);
            dto.setStatusSelect(video.getStatusSelect());
            dtoList.add(dto);
        }

        PageInfo<Video> page = new PageInfo(videoPage);
        model.addAttribute("page", page);
        model.addAttribute("dtoList", dtoList);
        model.addAttribute("companyList", companyList);
        model.addAttribute("programTypeList", programTypeList);
        return "adSlot/editLabel";
    }

    /**
     * @param model
     * @param videoId
     * @return String
     * @throws
     *
     * @title editAdSlotLabel
     * @description TODO 编辑广告位标签画面
     * @author mxy
     * @date 2017-03-31 19:47
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/editAdSlotLabel")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "编辑广告位标签画面")
    public String editAdSlotLabel(Model model, @RequestParam("videoId") Long videoId) {
        log.info("editAdSlotLabel......");

        //根据获取单个视频广告位相关标签信息
        List<AdSlot> adSlotList4Label = adSlotService.getLabel4AdSlot(null, videoId, null, null, null, null, null);
        Map<Long, AdSlot> adSlotMap = new HashMap();
        for (AdSlot adSlot : adSlotList4Label) {
            adSlotMap.put(adSlot.getId(), adSlot);
        }
        // 获取单个视频场景广告位信息
        List<Scene> sceneList = sceneService.getScene4AdsList(videoId, null, null);
        // 封装AdSlotDTO
        for (Scene scene : sceneList) {
            List<AdSlotDTO> DTOList = Lists.newArrayList();
            List<AdSlot> adSlotList = scene.getAdSlots();
            for (AdSlot adSlot : adSlotList) {
                AdSlot temp = adSlotMap.get(adSlot.getId());
                AdSlotDTO dto = new AdSlotDTO();
                dto.setAdSlotId(adSlot.getId());
                dto.setCreated(DateUtils.format(adSlot.getCreated()));
                dto.setVideoId(videoId);
                dto.setVideoName(adSlot.getVideo().getName());
                dto.setVideoPosition(adSlot.getVideoPosition() + "");
                dto.setVideoPositionTime(second2Time(adSlot.getVideoPosition()));
                //dto.setImageServer(adSlot.getImageServer());
                List<LabelDTO> labelDTOList = Lists.newArrayList();
                LabelDTO labelDTO;
                for (Scene scene1 : temp.getScenes()) {
                    labelDTO = new LabelDTO();
                    labelDTO.setLabelId(scene1.getId());
                    labelDTO.setLabelName(scene1.getName());
                    labelDTO.setLabelType(1);
                    labelDTOList.add(labelDTO);
                }
                for (Person person : temp.getPersons()) {
                    labelDTO = new LabelDTO();
                    labelDTO.setLabelId(person.getId());
                    labelDTO.setLabelName(person.getName());
                    labelDTO.setLabelType(2);
                    labelDTOList.add(labelDTO);
                }
                for (RecognitionObject recognitionObject : temp.getObjects()) {
                    labelDTO = new LabelDTO();
                    labelDTO.setLabelId(recognitionObject.getId());
                    labelDTO.setLabelName(recognitionObject.getName());
                    labelDTO.setLabelType(3);
                    labelDTOList.add(labelDTO);
                }
                for (SlotLabel slotLabel : temp.getSlotLabels()) {
                    labelDTO = new LabelDTO();
                    labelDTO.setLabelId(slotLabel.getId());
                    labelDTO.setLabelName(slotLabel.getName());
                    labelDTO.setLabelType(4);
                    labelDTOList.add(labelDTO);
                }
                String imageServer = adSlot.getImageServer();
                String imgPathPrefix;
                if (null != imageServer) {
                    imageServer = imageServer.substring(
                            imageServer.lastIndexOf(".") + 1, imageServer.length());
                    imgPathPrefix = "http://api.xiaobaishiji.com:81/image/"
                            + imageServer + "/" + videoId + "/";
                } else {
                    imgPathPrefix = "http://101.200.207.204:8080/thumbnail/" + videoId
                            + "/";
                }
                Integer timeIn = (adSlot.getVideoPosition() - 0) >= 0 ? (adSlot.getVideoPosition() - 0) : 1;//由于抽帧全部减0秒
                dto.setPictureAddress(imgPathPrefix + timeIn + ".jpg");//由于抽帧全部减0秒
                dto.setLabelDTOList(labelDTOList);
                DTOList.add(dto);
            }
            scene.setAdSlotDTOList(DTOList);
        }
        // 获取单个视频明星广告位信息
        List<Person> personList = personService.getPerson4AdsList(videoId, null, null);
        // 封装AdSlotDTO
        for (Person person : personList) {
            List<AdSlotDTO> DTOList = Lists.newArrayList();
            List<AdSlot> adSlotList = person.getAdSlots();
            for (AdSlot adSlot : adSlotList) {
                AdSlot temp = adSlotMap.get(adSlot.getId());
                AdSlotDTO dto = new AdSlotDTO();
                dto.setAdSlotId(adSlot.getId());
                dto.setCreated(DateUtils.format(adSlot.getCreated()));
                dto.setVideoId(videoId);
                dto.setVideoName(adSlot.getVideo().getName());
                dto.setVideoPosition(adSlot.getVideoPosition() + "");
                dto.setVideoPositionTime(second2Time(adSlot.getVideoPosition()));
                //dto.setImageServer(adSlot.getImageServer());
                List<LabelDTO> labelDTOList = Lists.newArrayList();
                LabelDTO labelDTO;
                for (Scene scene1 : temp.getScenes()) {
                    labelDTO = new LabelDTO();
                    labelDTO.setLabelId(scene1.getId());
                    labelDTO.setLabelName(scene1.getName());
                    labelDTO.setLabelType(1);
                    labelDTOList.add(labelDTO);
                }
                for (Person person1 : temp.getPersons()) {
                    labelDTO = new LabelDTO();
                    labelDTO.setLabelId(person1.getId());
                    labelDTO.setLabelName(person1.getName());
                    labelDTO.setLabelType(2);
                    labelDTOList.add(labelDTO);
                }
                for (RecognitionObject recognitionObject : temp.getObjects()) {
                    labelDTO = new LabelDTO();
                    labelDTO.setLabelId(recognitionObject.getId());
                    labelDTO.setLabelName(recognitionObject.getName());
                    labelDTO.setLabelType(3);
                    labelDTOList.add(labelDTO);
                }
                for (SlotLabel slotLabel : temp.getSlotLabels()) {
                    labelDTO = new LabelDTO();
                    labelDTO.setLabelId(slotLabel.getId());
                    labelDTO.setLabelName(slotLabel.getName());
                    labelDTO.setLabelType(4);
                    labelDTOList.add(labelDTO);
                }
                String imageServer = adSlot.getImageServer();
                String imgPathPrefix;
                if (null != imageServer) {
                    imageServer = imageServer.substring(
                            imageServer.lastIndexOf(".") + 1, imageServer.length());
                    imgPathPrefix = "http://api.xiaobaishiji.com:81/image/"
                            + imageServer + "/" + videoId + "/";
                } else {
                    imgPathPrefix = "http://101.200.207.204:8080/thumbnail/" + videoId
                            + "/";
                }
                Integer timeIn = (adSlot.getVideoPosition() - 0) >= 0 ? (adSlot.getVideoPosition() - 0) : 1;//由于抽帧全部减0秒
                dto.setPictureAddress(imgPathPrefix + timeIn + ".jpg");//由于抽帧全部减0秒
                dto.setLabelDTOList(labelDTOList);
                DTOList.add(dto);
            }
            person.setAdSlotDTOList(DTOList);
        }

        // 获取单个视频信息
        Video video = videoService.selectById(videoId);
        model.addAttribute("video", video);
        model.addAttribute("sceneList", sceneList);
        model.addAttribute("personList", personList);
        return "adSlot/editAdSlotLabel";
    }

    /**
     * @param videoId
     * @param results
     * @return JsonResult
     * @throws
     *
     * @title saveEditAdSlotLabel
     * @description TODO 保存广告位标签结果
     * @author mxy
     * @date 2017-03-31 20:03
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping("/saveEditAdSlotLabel")
    @ResponseBody
    @RequiresPermissions(value = {AuthorityConstants.AdSlot.PERMISSION_EDITADSLOTLABEL_SAVEEDITADSLOTLABEL}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "保存广告位标签结果")
    public JsonResult saveEditAdSlotLabel(
            @RequestParam(value = "videoId") Long videoId, @RequestParam(value = "results",required = false) String results) {
        log.info("saveEditAdSlotLabel......");
        if (string2Null(results)==null) {//如果无操作则直接返回
            jr = renderSuccess();
            return jr;
        }
        String[] resultX = results.split(",");
        AdSlot adSlot = adSlotService.selectById(videoId);
        adSlot.setSceneIds("0");
        adSlot.setPersonIds("0");
        adSlot.setObjectIds("0");
        adSlot.setSlotLabelIds("0");
        for (String s : resultX) {
            switch(s.substring(0,1)) {
                case "1":
                    adSlot.setSceneIds(adSlot.getSceneIds()+","+s);
                    break;
                case "2":
                    adSlot.setPersonIds(adSlot.getPersonIds()+","+s);
                    break;
                case "3":
                    adSlot.setObjectIds(adSlot.getObjectIds()+","+s);
                    break;
                case "4":
                    adSlot.setSlotLabelIds(adSlot.getSlotLabelIds()+","+s);
                    break;
                default:

                    break;
            }
        }
        try {
            adSlotService.updateById(adSlot);
            jr = renderSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError(e.getMessage());
        }
        return jr;
    }


    /**
     * @Title: adShelfOnList
	 * @Description: 查询广告位列表
	 * @author liuxiaolong
     * @date 2017/3/22
     * @param pageNum 当前页码
     * @param pageSize 每页数据量
     * @param adId 广告ID
     * @param programLevelId 节目等级ID
     * @param sceneId 场景ID
     * @param personId 明星ID
     * @param videoCompanyId 平台公司ID
     * @param videoTypeId 视频类型ID
     * @param videoName 视频名称
     * @param labelId 标签id
     * @param chooseIds 当前页选中的广告位ID
     * @param currentPageIds 当前页所有的广告位ID
     * @param lastChooseIds 其他页选中的广告位ID
     * @param companyId 父页广告公司ID
     * @param styleId 父页广告样式ID
     * @param adName 父页广告名字
     * @param parentPageNum 父页所属页码
     * @param parentPageSize 父页每页数据
     * @return
     * @exception
     * @modifier
     * @remark
     * @version V1.0
     */
	@RequestMapping(value="/adShelfOnList")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告投放中广告位列表页面信息")
    public String adShelfOnList(@RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
					            @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
					            Long adId,
					            @RequestParam(required = false) Long programLevelId,
					            @RequestParam(required = false) Long sceneId,
					            @RequestParam(required = false) Long personId,
					            @RequestParam(required = false) Long videoCompanyId,
					            @RequestParam(required = false) Long videoTypeId,
					            @RequestParam(required = false) String videoName,
					            @RequestParam(required = false) Long labelId,
					            @RequestParam(required = false) String chooseIds,
					            @RequestParam(required = false) String currentPageIds,
					            @RequestParam(required = false) String lastChooseIds,
					            @RequestParam(required = false) Long companyId,
					            @RequestParam(required = false) Integer styleId,
					            @RequestParam(required = false) String adName,
					            @RequestParam(required = false) Integer parentPageNum,
					            @RequestParam(required = false) Integer parentPageSize,
					            Model model) {
    	log.info("adShelfOnList......");
    	pageSize = 10;
    	
//    	if(org.apache.commons.lang3.StringUtils.isNotBlank(adName)){
//	    	String tempAd;
//			try {
//				tempAd = new String(adName.getBytes("ISO-8859-1"),"utf-8");
//				adName = URLDecoder.decode(tempAd, "utf-8"); 
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}  
//    	}
    	
//    	if(org.apache.commons.lang3.StringUtils.isNotBlank(videoName)){
//			String tempVideo;
//			try {
//				tempVideo = new String(videoName.getBytes("ISO-8859-1"),"utf-8");
//				videoName = URLDecoder.decode(tempVideo, "utf-8"); 
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}  
//    	}
    	
    	//查询广告位
		Page<AdSlotOfAdDTO> adSlotPage = adSlotService.selectAdSlotPage(pageNum, pageSize, programLevelId, sceneId, personId, videoCompanyId, videoTypeId, videoName, labelId);
		PageInfo pageInfo = new PageInfo(adSlotPage);
		//广告位列表
		List<AdSlotOfAdDTO> adSlotList = adSlotPage.getRecords();
		
		for(int i=0;i<adSlotList.size();i++){
			List<VideoAdSlotDTO> videoAdSlotList = adSlotService.selectAdSlotSingle(adSlotList.get(i).getVideoId());
			for(VideoAdSlotDTO videoAdSlotDTO : videoAdSlotList){
				videoAdSlotDTO.setVideoPosition(second2Time(Integer.parseInt(videoAdSlotDTO.getVideoPosition())));
			}
			
			Long videoId = adSlotList.get(i).getVideoId();
			// 获取单个视频的场景识别信息
	        List<SceneRecognitionResult> srrlist = sceneRecognitionResultService.getSceneRecognitionResultList(videoId,null);
	        // 封装Scene地址
	        for (SceneRecognitionResult sceneRecognitionResult : srrlist) {
	            String videoPosition = adSlotList.get(i).getVideoPosition();
                String imageServer = sceneRecognitionResult.getImageServer();
                String imgPathPrefix;
                if (null != imageServer) {
                    imageServer = imageServer.substring(
                            imageServer.lastIndexOf(".") + 1, imageServer.length());
                    imgPathPrefix = "http://api.xiaobaishiji.com:81/image/"
                            + imageServer + "/" + videoId + "/";
                } else {
                    imgPathPrefix = "http://101.200.207.204:8080/thumbnail/" + videoId
                            + "/";
                }
                Integer timeIn = (Integer.parseInt(videoPosition) - 0) >= 0 ? (Integer
                        .parseInt(videoPosition) - 0) : 1;//由于抽帧全部减0秒
                adSlotList.get(i).setPictureAdress(imgPathPrefix + timeIn + ".jpg");
	            
	        }
	        
			adSlotList.get(i).setVideoAdSlot(videoAdSlotList);
			adSlotList.get(i).setVideoPosition(second2Time(Integer.parseInt(adSlotList.get(i).getVideoPosition())));
		}
		
        //收索框
		List<AdSlotSearchDTO> searchList = adSlotService.selectAdSlotSearch();
		
		/**
		 *得到其他页面选中的广告位 
		 */
		//从所有选中的广告位（包括其他页面选中的广告位）中过滤掉当前页面的广告位，然后在重新添加当前页新选中的广告位，目的：如果用户去除以前选中的广告位，那么可以将他去除的广告位从所有选中的广告位中移除
		String replaceLastChooseids = lastChooseIds;
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(currentPageIds)){
			String[] chooseids = currentPageIds.split(",");
			for (String str : chooseids) {  
				replaceLastChooseids = replaceLastChooseids.replace(str, "");
			}
		}
		//添加本页新选中的广告位
		List<String> chooseList=new ArrayList<String>();
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(chooseIds)){
			String[] chooseids = chooseIds.split(",");
			for (String str : chooseids) {  
				chooseList.add(str);
			}
		}
		//去除多余逗号,保证字符串长度不能过长
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(replaceLastChooseids)){
			String[] chooseids = replaceLastChooseids.split(",");
			for (String str : chooseids) {  
				chooseList.add(str);
			}
		}
		
		//拼接新的字符串
		String lastIds = "";
		for (String choseids : chooseList) {  
			lastIds =  lastIds + choseids +",";
		}
//       System.out.println("=================lastIds==================="+lastIds);
		
		//场景
		List<Scene> sceneList =  sceneService.getScenes(null, null);
		
		
		//输出日志
		if(log.isDebugEnabled()){
			log.info("adShelfOnList......");
			log.debug("adShelfOnList......pageNum:{}", pageNum);
			log.debug("adShelfOnList......pageSize:{}", pageSize);
			log.debug("adShelfOnList......adId:{}", adId);
			log.debug("adShelfOnList......programLevelId:{}", programLevelId);
			log.debug("adShelfOnList......sceneId:{}", sceneId);
			log.debug("adShelfOnList......personId:{}", personId);
			log.debug("adShelfOnList......videoCompanyId:{}", videoCompanyId);
			log.debug("adShelfOnList......videoTypeId:{}", videoTypeId);
			log.debug("adShelfOnList......videoName:{}", videoName);
			log.debug("adShelfOnList......labelId:{}", labelId);
			
			log.debug("adShelfOnList......chooseIds:{}", chooseIds);
			log.debug("adShelfOnList......currentPageIds:{}", currentPageIds);
			log.debug("adShelfOnList......lastChooseIds:{}", lastChooseIds);
			log.debug("adShelfOnList......companyId:{}", companyId);
			log.debug("adShelfOnList......styleId:{}", styleId);
			log.debug("adShelfOnList......adName:{}", adName);
			log.debug("adShelfOnList......parentPageNum:{}", parentPageNum);
			log.debug("adShelfOnList......parentPageSize:{}", parentPageSize);
			
			log.debug("adShelfOnList......result adSlotList size:{}", adSlotPage.getRecords().size());
		}

		//页面传值
		model.addAttribute("adId", adId);
		model.addAttribute("programLevelId", programLevelId);
		model.addAttribute("sceneId", sceneId);
		model.addAttribute("personId", personId);
		model.addAttribute("videoCompanyId", videoCompanyId);
		model.addAttribute("videoTypeId", videoTypeId);
		model.addAttribute("videoName", videoName);
		model.addAttribute("labelId", labelId);
		model.addAttribute("chooseIds", chooseIds);
		model.addAttribute("lastChooseIds", lastIds);
		
		model.addAttribute("parentPageNum", parentPageNum);
		model.addAttribute("parentPageSize", parentPageSize);
		model.addAttribute("adName", adName);
		model.addAttribute("styleId", styleId);
		model.addAttribute("companyId", companyId);
		
		model.addAttribute("page", pageInfo);
		model.addAttribute("adSlotList", adSlotList);
		model.addAttribute("searchList", searchList);
		model.addAttribute("chooseList", chooseList);
		model.addAttribute("sceneList", sceneList);
    	return "ad/adShelfOnList";
	}

    

    /**
     * @Title: adShelfOn
	 * @Description: 投放广告位操作
	 * @author liuxiaolong
     * @date 2017/3/23
     * @param adId 广告ID
     * @param ids  广告位列表
     * @return
     * @exception
     * @modifier
     * @remark
     * @version V1.0
     */
	@RequestMapping("/adShelfOn")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告投放中广告位的投放操作")
    @ResponseBody
    public JsonResult adShelfOn(Long adId,String ids) {
    	log.info("adShelfOn......");

    	//将广告位id字符串分割变放入list中
    	String[] idArray = ids.split(",");
    	List idList = new ArrayList();
    	for(int i=0;i<idArray.length;i++){
    		idList.add(idArray[i]);
    	}

    	//跟新广告位表中广告的ID和广告表中的投放时间
    	boolean updateAdSlotAdid = adSlotService.updateAdSlotAdid(adId,idList);
    	boolean updateAdStartTime = adService.updateAdStartTime(DateUtils.getDateTime(), adId);
    	boolean updateAdOrderSlotCount = adOrderService.updateAdOrderSlotCount(adId);
    	//记录日志
    	if(log.isDebugEnabled()){
			log.info("adShelfOn......");
			log.debug("adShelfOn......adId:{}", adId);
			log.debug("adShelfOn......ids:{}", ids);
			log.debug("adShelfOn......param ids toString:{}", idList.toArray());
		}

    	if(updateAdSlotAdid && updateAdStartTime && updateAdOrderSlotCount){
    		jr = renderSuccess("操作成功！");
        } else {
            jr = renderError("操作失败！");
        }

		return jr;
	}

    /**
	 * @Title: adShelfOffList
	 * @Description: 查询带下架广告位
	 * @author liuxiaolong
     * @date 2017/3/23
	 * @param pageNum 当前页码
	 * @param pageSize 每页数据量
	 * @param videoName 视频名称
	 * @param videoCompanyId 视屏平台ID
	 * @param companyId 父页广告公司ID
     * @param styleId 父页广告样式ID
     * @param adName 父页广告名字
     * @param parentPageNum 父页所属页码
     * @param parentPageSize 父页每页数据
	 * @return
	 * @exception
     * @modifier
     * @remark
     * @version V1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/adShelfOffList")
	@SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告位下架列表信息")
	public String adShelfOffList(@RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
						            @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
						            @RequestParam(required = false) String videoName,
						            @RequestParam(required = false) Long videoCompanyId,
						            @RequestParam(required = false) Long companyId,
						            @RequestParam(required = false) Integer styleId,
						            @RequestParam(required = false) String adName,
						            @RequestParam(required = false) Integer parentPageNum,
						            @RequestParam(required = false) Integer parentPageSize,
						            Long adId,Model model) {
		log.info("adShelfOffList......");
		
//		if(org.apache.commons.lang3.StringUtils.isNotBlank(videoName)){
//			String tempVideo;
//			try {
//				tempVideo = new String(videoName.getBytes("ISO-8859-1"),"utf-8");
//				videoName = URLDecoder.decode(tempVideo, "utf-8"); 
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}
		//查询待下架的广告位
		Page<AdSlotOfAdDTO> offAdPage = adSlotService.selectOffShelfAdPage(pageNum, pageSize, videoName, videoCompanyId, adId);
		PageInfo pageInfo = new PageInfo(offAdPage);
		//广告位列表
		List<AdSlotOfAdDTO> offAdList = offAdPage.getRecords();
		for(AdSlotOfAdDTO adSlotOfAdDTO : offAdList){
			adSlotOfAdDTO.setVideoPosition(second2Time(Integer.parseInt(adSlotOfAdDTO.getVideoPosition())));
		}
		
		//视频平台
		EntityWrapper<Company> entityWrapper = new EntityWrapper<>();
		entityWrapper.eq("deleted", "0").eq("status", "3").eq("company_type","1");
		List<Company> companyList = companyService.selectList(entityWrapper);

		//记录日志
		if(log.isDebugEnabled()){
			log.info("adShelfOffList......");
			log.debug("adShelfOffList......pageNum:{}", pageNum);
			log.debug("adShelfOffList......pageSize:{}", pageSize);
			log.debug("adShelfOffList......videoName:{}", videoName);
			log.debug("adShelfOffList......videoCompanyId:{}", videoCompanyId);
			log.debug("adShelfOffList......companyId:{}", companyId);
			log.debug("adShelfOffList......styleId:{}", styleId);
			log.debug("adShelfOffList......adName:{}", adName);
			log.debug("adShelfOffList......parentPageNum:{}", parentPageNum);
			log.debug("adShelfOffList......parentPageSize:{}", parentPageSize);
			log.debug("adShelfOffList......result offAdList size :{}", offAdPage.getRecords().size());
		}

		//页面传值
		model.addAttribute("videoName", videoName);
		model.addAttribute("videoCompanyId", videoCompanyId);
		model.addAttribute("adId", adId);
		model.addAttribute("page", pageInfo);
		model.addAttribute("offAdList", offAdList);
		model.addAttribute("companyList", companyList);
		
		model.addAttribute("parentPageNum", parentPageNum);
		model.addAttribute("parentPageSize", parentPageSize);
		model.addAttribute("adName", adName);
		model.addAttribute("styleId", styleId);
		model.addAttribute("companyId", companyId);

		return "ad/adShelfOffList";
	}

    /**
     * @Title: adShelfOff
	 * @Description: 下架选中的广告的广告位
	 * @author liuxiaolong
     * @date 2017/3/23
     * @param ids 选中的广告位ID
     * @return
     * @exception
     * @modifier
     * @remark
     * @version V1.0
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/adShelfOff")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "下架选中的广告的广告位")
    public JsonResult adShelfOff(String ids){
    	log.info("adShelfOff......");

    	//将广告位id字符串分割变放入list中
    	String[] idArray = ids.split(",");
    	List idList = new ArrayList();
    	for(int i=0;i<idArray.length;i++){
    		idList.add(idArray[i]);
    	}

    	boolean updateSlotAdid = adSlotService.updateAdSlotByChoosAd(idList);

    	if(log.isDebugEnabled()){
			log.info("adShelfOff......");
			log.debug("adShelfOff......ids", ids);
		}
    	
    	
    	if(updateSlotAdid){
    		jr = renderSuccess("操作成功！");
        } else {
            jr = renderError("操作失败！");
        }

		return jr;
    }


    /**
     * @Title: adShelfOffExport
	 * @Description: 导出选中的广告位
	 * @author liuxiaolong
     * @date 2017/3/26
     * @param adId 广告ID
     * @param adSlotIds 选中广告位ID
     * @return
     * @exception
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping("/adShelfOffExport")
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "导出选中的广告位")
	public void adShelfOffExport(HttpServletResponse response,Long adId,String adSlotIds) {
    	log.info("adShelfOffExport......");
    	//将广告位id字符串分割变放入list中
    	String[] idArray = adSlotIds.split(",");
    	List idList = new ArrayList();
    	for(int i=0;i<idArray.length;i++){
    		idList.add(idArray[i]);
    	}

    	//查询选中的广告位列表
    	List<AdSlotOfAdDTO> adSlotList = adSlotService.selectOffShelfAdList(idList);
    	for(AdSlotOfAdDTO adSlotOfAdDTO : adSlotList){
    		adSlotOfAdDTO.setVideoPosition(second2Time(Integer.parseInt(adSlotOfAdDTO.getVideoPosition())));
    	}
    	//获得广告名字
    	String adName = adService.selectById(adId).getName();

    	//生产excel 对象
    	HSSFWorkbook workbook = adSlotService.createExcel(adSlotList);
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8"); 
            String fileName = adName + DateUtils.getDateTimeStr();
        	fileName=new String((fileName+".xls").getBytes(), "ISO8859-1"); 
        	response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        	ServletOutputStream fout = response.getOutputStream();
        	workbook.write(fout);
            fout.flush();  
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if(log.isDebugEnabled()){
			log.info("adShelfOffExport......");
			log.debug("adShelfOffExport......adId", adId);
			log.debug("adShelfOffExport......adSlotIds", adSlotIds);
		}
	}
	
	
    
}