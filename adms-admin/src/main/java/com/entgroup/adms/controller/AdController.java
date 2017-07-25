package com.entgroup.adms.controller;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.entgroup.adms.model.system.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.conf.AdCreateConfig;
import com.entgroup.adms.dto.AdDTO;
import com.entgroup.adms.dto.AdSlotOfAdDTO;
import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.util.PageInfo;



@Controller
@RequestMapping("/ad")
public class AdController extends BaseController{

	
	/**
     * 系统模块名
     */
    private static final String SYSTEM_MODULE = "广告管理";
	
	/**
	 * @Title: adVerifyList
	 * @Description: 得到待审核广告列表
	 * @author liuxiaolong
     * @date 2017/3/21
	 * @param pageNum  页码
	 * @param pageSize 每页数据行数
	 * @param companyId 公司id
	 * @param adName 广告名称
	 * @return String 
	 * @exception
     * @modifier
     * @remark
     * @version V1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/adVerify")
	@SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告的审核列表页面信息")
	public String adVerifyList(Model model,@RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
                                  @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
                                  @RequestParam(required = false) Long companyId,
                                  @RequestParam(required = false) String adName) {
		log.info("adVerifyList......");
//		if(StringUtils.isNotBlank(adName)){
//			String temp;
//			try {
//				temp = new String(adName.getBytes("ISO-8859-1"),"utf-8");
//				adName = URLDecoder.decode(temp, "utf-8"); 
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}  
//		}
		//查询待审核的广告列表的分页信息
		Page<AdDTO> selectAdPage = adService.selectAudiAdPage(pageNum, pageSize, companyId, adName,1,null,null,null,null);
		PageInfo pageInfo = new PageInfo(selectAdPage);
		//待审核的广告列表
		List<AdDTO> adList = selectAdPage.getRecords();
		//广告原因
		List<AdReasonTemplate> adReasonTemplateList = adReasonTemplateService.selectList(null);
		
		//客户列表
		EntityWrapper<Company> entityWrapper = new EntityWrapper<>();
		entityWrapper.eq("deleted", "0").eq("status", "3").eq("company_type","2");
		List<Company> companyList = companyService.selectList(entityWrapper);
		
		//日志记录
		if(log.isDebugEnabled()){
			log.info("adVerifyList......");
			log.debug("adVerifyList......pageNum:{}", pageNum);
			log.debug("adVerifyList......pageSize:{}", pageSize);
			log.debug("adVerifyList......companyId:{}", companyId);
			log.debug("adVerifyList......adName:{}", adName);
			log.debug("adVerifyList......result selectAdlist size:{}", selectAdPage.getRecords().size());
		}
		
		// 页面传值
		model.addAttribute("companyId", companyId);
		model.addAttribute("adName", adName);
        model.addAttribute("adList", adList);
        model.addAttribute("page", pageInfo);
        model.addAttribute("companyList", companyList);
        model.addAttribute("adReasonTemplateList", adReasonTemplateList);
		return "ad/adVerifyList";
	}
	
	/**
	 * @Title: getSingleAd
	 * @Description: 审核广告基本信息
	 * @author liuxiaolong
     * @date 2017/3/21
	 * @param adId 广告ID
	 * @return
	 * @exception
     * @modifier
     * @remark
     * @version V1.0
	 */
	@RequestMapping(value="/getSingleAd",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	@SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告的审核页面信息")
	@ResponseBody
	public String getSingleAd(Long adId,Model model) {
		
		log.info("getSingleAd......");
		//查询广告
		Ad ad = adService.selectAdById(adId);
		ad.setImagePath(AdCreateConfig.config.ipPort()+AdCreateConfig.config.uploadFolder()+ad.getImagePath());
		
		if(log.isDebugEnabled()){
			log.info("getSingleAd......");
			log.debug("getSingleAd......adId:{}", adId);
			log.debug("getSingleAd......result ad info:{}", ad);
		}
		
		// 页面传值
		model.addAttribute("adId", adId);
        model.addAttribute("ad", ad);
        
		return JSON.toJSONString(model);
	}
	
	/**
	 * @Title: saveAdVerifyResult
	 * @Description: 广告审核操作
	 * @author liuxiaolong
     * @date 2017/3/22
	 * @param adId 广告ID
	 * @param adStatus 广告状态
	 * @param reasonIds 不合格原因id
	 * @param otherReason 其他不合格原因 
	 * @return
	 * @exception
     * @modifier
     * @remark
     * @version V1.0
	 */
	@RequestMapping("/saveAdVerifyResult")
	@SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告的审核通过操作")
	public String saveAdVerifyResult(Long adId,
			                        Integer adStatus,
			                        @RequestParam(required = false) String reasonIds,
			                        @RequestParam(required = false) String otherReason,
			                        Model model ) {
		//System.out.println("=====================测试=========================");
		log.info("saveAdVerifyResult......");

		adService.updateAdStatus(adStatus, adId);
		if(adStatus==3){
			AdReason adReason = new AdReason();
			adReason.setAdId(adId);
			adReason.setReasonIds(reasonIds);
			adReason.setOtherReason(otherReason);
			adReasonService.insert(adReason);
		}

		model.addAttribute("adId", adId);
		model.addAttribute("status", adStatus);
		if(log.isDebugEnabled()){
			log.info("saveAdVerifyResult......");
			log.debug("saveAdVerifyResult......adId:{}",adId);
		}

		
		return "redirect:/notice/addNotice";
	}
	
	/**
	 * @Title: adShelfList
	 * @Description: 查询广告投放列表
	 * @author liuxiaolong
     * @date 2017/3/22
	 * @param pageNum 当前页码
	 * @param pageSize 每页数据量
	 * @param companyId 公司ID
	 * @param styleId 广告类型ID
	 * @param adName 广告名称
	 * @return
	 * @exception
     * @modifier
     * @remark
     * @version V1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/adShelf")
	@SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告的投放列表页面信息")
	public String adShelfList(@RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
						            @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
						            @RequestParam(required = false) Long companyId,
						            @RequestParam(required = false) Long styleId,
	                                @RequestParam(required = false) String adName,Model model) {
		log.info("adShelfList......");
//		if(StringUtils.isNotBlank(adName)){
//			String temp;
//			try {
//				temp = new String(adName.getBytes("ISO-8859-1"),"utf-8");
//				adName = URLDecoder.decode(temp, "utf-8"); 
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}  
//		}
		//查询待投放的广告
		Page<AdDTO> delivAdPage = adService.selectDeliveAdPage(pageNum, pageSize, companyId, adName, styleId);
		PageInfo pageInfo = new PageInfo(delivAdPage);
		//待投放的广告列表
		List<AdDTO> delivAdList = delivAdPage.getRecords();
		//客户列表
		EntityWrapper<Company> entityWrapper = new EntityWrapper<>();
		entityWrapper.eq("deleted", "0").eq("status", "3").eq("company_type","2");
		List<Company> companyList = companyService.selectList(entityWrapper);
		//广告样式列表
		List<AdStyle> adStyleList = adStyleService.selectList(null);
		
		if(log.isDebugEnabled()){
			log.info("adShelfList......");
			log.debug("adShelfList......pageNum:{}", pageNum);
			log.debug("adShelfList......pageSize:{}", pageSize);
			log.debug("adShelfList......companyId:{}", companyId);
			log.debug("adShelfList......adName:{}", adName);
			log.debug("adShelfList......styleId:{}", styleId);
			log.debug("adShelfList......result delivAdList size :{}", delivAdPage.getRecords().size());
		}
		
		//页面传值
		model.addAttribute("companyId", companyId);
		model.addAttribute("styleId", styleId);
		model.addAttribute("adName", adName);
		model.addAttribute("page", pageInfo);
		model.addAttribute("delivAdList", delivAdList);
		model.addAttribute("companyList", companyList);
		model.addAttribute("adStyleList", adStyleList);

		return "ad/adShelfList";
	}
	
	/**
	 * @Title: adList
	 * @Description: 得到广告列表
	 * @author liuxiaolong
     * @date 2017/4/6
	 * @param pageNum  页码
	 * @param pageSize 每页数据行数
	 * @return String 
	 * @exception
     * @modifier
     * @remark
     * @version V1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/adList")
	@SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告的审核列表页面信息")
	public String adList(Model model,
						 @RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
						 @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
						 @ModelAttribute("ad") Ad ad) {
		log.info("adList......");
		//查询待审核的广告列表的分页信息
		User shiroUser = getShiroUser();
		Long companyId = shiroUser.getCompanyId();
		//System.out.println("====================="+userId);
//		if(StringUtils.isNotBlank(adName)){
//			String temp;
//			try {
//				temp = new String(adName.getBytes("ISO-8859-1"),"utf-8");
//				adName = URLDecoder.decode(temp, "utf-8"); 
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}  
//		}
		ad.setCompanyId(companyId);
		Page<Ad> adListPage = new Page<>(pageNum, pageSize);
		adListPage = adService.adList(adListPage, ad);
		PageInfo pageInfo = new PageInfo(adListPage);
		//待审核的广告列表
		List<Ad> adList = adListPage.getRecords();

		for (Ad adTemp : adList) {
			AdOrder adOrder = adOrderService.selectById(adTemp.getAdOrderId());
			String orderName = null;
			if (null != adOrder) {
				orderName = adOrder.getOrderName();
			}
			adTemp.setAdOrderName(orderName);
		}

		//广告样式
		List<AdStyle> styleList = adStyleService.selectList(null);
		
		//日志记录
		if(log.isDebugEnabled()){
			log.info("adList......");
			log.debug("adList......pageNum:{}", pageNum);
			log.debug("adList......pageSize:{}", pageSize);
			log.debug("adList......result selectAdlist size:{}", adListPage.getRecords().size());
		}
		
		// 页面传值
		model.addAttribute("ad", ad);
		model.addAttribute("styleList", styleList);
	    model.addAttribute("adList", adList);
	    model.addAttribute("page", pageInfo);
		return "ad/adList";
	}
	
	/**
	 * @Title: adDetail
	 * @Description: 查看选中广告
	 * @author liuxiaolong
     * @date 2017/4/6
	 * @param adId 广告id
	 * @return String 
	 * @exception
     * @modifier
     * @remark
     * @version V1.0
	 */
	@RequestMapping(value="/adDetail",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	@SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "查看广告广告位")
	@ResponseBody
	public String adDetail(Model model, Long adId) {
		log.info("adDetail......");
		//查询广告
		Ad ad = adService.selectById(adId);

		OrderAd orderAd = null;
		AdOrder adOrder = null;
		EntityWrapper<OrderAd> orderAdEntityWrapper = new EntityWrapper<>();
		EntityWrapper<AdOrder> adOrderEntityWrapper = new EntityWrapper<>();
		String orderName = "";

		//因测试需要，将地址占时写为相对路径，以后可根据实际的服务器地址映射进行此处的修改
		if(ad !=null){
			ad.setImagePath(AdCreateConfig.config.ipPort()+AdCreateConfig.config.uploadFolder()+ad.getImagePath());

			orderAd = orderAdService.selectOne(orderAdEntityWrapper.eq("ad_id", adId));
			try {
				if (null == orderAd) {
					System.err.println("ORDERAD IS NULL");
				}
				adOrder = adOrderService.selectById(orderAd.getOrderId());
				orderName = adOrder.getOrderName();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//查询广告下所有的广告位
		List<AdSlotOfAdDTO> adSlotOfAd = adSlotService.selectAdSlotByAdId(adId);
	    
		for(AdSlotOfAdDTO adSlotOfAdDTO : adSlotOfAd){
			adSlotOfAdDTO.setVideoPosition(second2Time(Integer.parseInt(adSlotOfAdDTO.getVideoPosition())));
		}
		
		//日志记录
		if(log.isDebugEnabled()){
			log.info("adDetail......");
			log.debug("adDetail......adId:{}", adId);
			log.debug("adDetail......result ad toString:{}", ad.toString());
		}
		
		// 页面传值
		
		model.addAttribute("ad", ad);
		model.addAttribute("adSlotOfAd", adSlotOfAd);
		model.addAttribute("orderName", orderName);
		return JSON.toJSONString(model);
	}
	
	/**
	 * @Title: adCreate
	 * @Description: 得到广告修改页面
	 * @author liuxiaolong
     * @date 2017/4/6
	 * @param adId  广告ID
	 * @return String 
	 * @exception
     * @modifier
     * @remark
     * @version V1.0
	 */
	@RequestMapping("/adCreate")
	@SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告修改页面")
	public String adCreate(Model model,@RequestParam(required = false)  Long adId) {
		log.info("adCreate......");
		Ad ad = adService.selectById(adId);
		String  lastImagePath = "";
		
		//因测试需要，将地址占时写为相对路径，以后可根据实际的服务器地址映射进行此处的修改
		if(ad !=null){
			lastImagePath = ad.getImagePath();
			ad.setImagePath(AdCreateConfig.config.ipPort()+AdCreateConfig.config.uploadFolder()+ad.getImagePath());
		}
		
		//广告类型
		List<AdStyle> adStyleList = adStyleService.selectList(null);
		//广告样式
		List<AdType> adTypeList = adTypeService.selectList(null);
		
		//日志记录
		if(log.isDebugEnabled()){
			log.info("adCreate......");
			log.debug("adCreate......adId:{}", adId);
			log.debug("adCreate......result to string:{}", ad);
		}
		
		// 页面传值
		model.addAttribute("lastImagePath",lastImagePath);
		model.addAttribute("adId", adId);
		model.addAttribute("adStyleList", adStyleList);
		model.addAttribute("adTypeList", adTypeList);
		model.addAttribute("ad", ad);
		return "ad/adCreate";
	}
		
	
	/**
	 * @Title: saveAdCreateResult
	 * @Description: 得到广告添加操作
	 * @author liuxiaolong
     * @date 2017/4/6
	 * @param ad  广告
	 * @return String 
	 * @exception
     * @modifier
     * @remark
     * @version V1.0
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/saveAdCreateResult",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告添加操作")
	@ResponseBody
	public JsonResult saveAdCreateResult(@ModelAttribute("ad") Ad ad,@RequestParam("up_img") MultipartFile up_img,HttpServletRequest request){
		log.info("saveAdCreateResult......");
		//广告名转码
//		try {
//			String adName = ad.getName();
//			String temp = new String(adName.getBytes("ISO-8859-1"),"utf-8");  
//			adName = URLDecoder.decode(temp, "utf-8"); 
//			ad.setName(adName);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// 非空校验
        String msg = adIsNull(ad);
        if (null != msg) {
            jr = renderError(msg);
            jr.setData("ad", ad);
            return jr;
        }
		
		User shiroUser = getShiroUser();
		
		//设置广告状态
		ad.setStatus(2);
		
		//传递过来的paramImagePath值
		String paramImagePath = ad.getImagePath();
		
		Long adid = ad.getId();
		//数据库中现有的imagePath值
		String existImagePath = "";
		if(adid != null){
			Ad existAd = adService.selectById(adid);
		    existImagePath = existAd.getImagePath();
		}
		
		//如果用户修改广告中没有重新上传图片则图片不用处理
		if(!paramImagePath.equals(existImagePath)){
			//图片上传
			String path = AdCreateConfig.config.uploadAddress();
			File f_path = new File(path);
			if(!f_path.exists()){
				f_path.mkdirs();
			}
			String originalName = up_img.getOriginalFilename();
			String suffixType = originalName.substring(originalName.lastIndexOf("."));
			String imageFileName =shiroUser.getId()+ shiroUser.getCompanyId() + new Date().getTime()+suffixType;// 生成处理后在图片名称
			File file = new File(path ,imageFileName);
			try {
				up_img.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ad.setImagePath(imageFileName);
		}
		
		//判断操作类型
	   boolean editAdInfo = false;
	   if(adid != null){
		   
		   //删除上次传的图片
		   Ad lastAd = adService.selectById(adid);
		   File lastFile = new File(lastAd.getImagePath());
		   if (lastFile.isFile() && lastFile.exists()) {
			   lastFile.delete();
		   }
		   
		    EntityWrapper<Ad> entityWrapper = new EntityWrapper<>();
			entityWrapper.eq("id", adid);
			editAdInfo = adService.update(ad,entityWrapper);
	   }else{
		    //广告添加者
		    ad.setOrderOnline(0);
			ad.setCreatorId(shiroUser.getId());
			ad.setCompanyId(shiroUser.getCompanyId());
			//添加广告时间
			ad.setCreated(new Date());
		    editAdInfo = adService.insert(ad);
	   }
		
		
		
		if(log.isDebugEnabled()){
			log.info("saveAdCreateResult......");
			log.debug("saveAdCreateResult......param ad to String:{}", ad.toString());
		}
		
		if(editAdInfo){
			jr = renderSuccess("广告制作成功！");
        } else {
            jr = renderError("广告制作失败！");
        }
		
		
		return jr;
	}
	
	/**
	 * @Title: adIsNull
	 * @Description: 广告添加内容校验
	 * @author liuxiaolong
     * @date 2017/4/15
	 * @param ad  广告
	 * @return String 
	 * @exception
     * @modifier
     * @remark
     * @version V1.0
	 * @throws 
	 */
	public String adIsNull(Ad ad) {

        String msg = null;

        if (StringUtils.isEmpty(ad.getName())) {
            msg = "请输入广告名称";
        } else if (StringUtils.isEmpty(ad.getStyleId()+"")){
            msg = "请选择广告类型";
        } else if (StringUtils.isEmpty(ad.getTypeId()+"")) {
            msg = "请选择广告样式";
        } else if (StringUtils.isEmpty(ad.getImagePath())) {
            msg = "请上传广告";
        } else if (StringUtils.isEmpty(ad.getLink())) {
            msg = "请输广告落地页";
        } 
        return msg;
    }
	
	/**
	 * @Title: checkAdName
	 * @Description: 校验广告名是否一样
	 * @author liuxiaolong
     * @date 2017/4/20
     * @param adId  广告名字
	 * @param adName  广告名字
	 * @param adTyle  广告类型
	 * @return String 
	 * @exception
     * @modifier
     * @remark
     * @version V1.0
	 * @throws 
	 */
	@RequestMapping(value="checkAdName",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	@SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告添加校验")
	@ResponseBody
	public JsonResult checkAdName(Long adId,String adName,Integer adTyle){
		log.info("checkAdName.......");
		
		User shiroUser = getShiroUser();
//		if(StringUtils.isNotBlank(adName)){
//			String temp;
//			try {
//				temp = new String(adName.getBytes("ISO-8859-1"),"utf-8");
//				adName = URLDecoder.decode(temp, "utf-8"); 
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}  
//		}
//		System.out.println("=========adName================"+adName);
		EntityWrapper<Ad> entityWrapper = new EntityWrapper<>();
		entityWrapper.eq("name", adName).eq("type_id", adTyle).eq("company_id", shiroUser.getCompanyId());
		List<Ad> adList = adService.selectList(entityWrapper);
		
		if(log.isDebugEnabled()){
			log.info("checkAdName.......");
			log.debug("checkAdName.......adName:{}"+adName);
			log.debug("checkAdName.......adTyle:{}"+adTyle);
			log.debug("checkAdName.......result adList to String :{}"+adList.toString());
		}
		
		if(StringUtils.isBlank(adId+"")&&adList.size()>0){//新增广告时ID参数为空，此时数据库中有该数据
			jr = renderError("该广告已存在");
			jr.setData("check", 1);
		}else if(StringUtils.isNotBlank(adId+"")&&adList.size()>1){//修改广告时id不为空，此时数据库中该数据条数只能为1
			jr = renderError("该广告已存在");
			jr.setData("check", 1);
		}else{
			jr.setData("check", 2);
		}
		return jr;
	}
	
}
