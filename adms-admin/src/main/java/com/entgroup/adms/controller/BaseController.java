package com.entgroup.adms.controller;

import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.model.system.User;
import com.entgroup.adms.service.*;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: hpb Date: Time: Describe:
 */
public class BaseController {

    @Autowired
    protected ServletContext servletContext;
    protected Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 数据库中假删除字段（deleted）默认值，用于查询或插入数据方法 value:0
     */
    protected static final Short DELETED_VALUE_OF_SELECT_OR_SAVE = Short.parseShort("0");

    /**
     * 数据库中假删除字段（deleted）默认值，用于删除或修改数据方法 value:1
     */
    protected static final Short DELETED_VALUE_OF_DELETE_OR_UPDATE = Short.parseShort("1");

    /**
     * 添加的广告默认为显示状态
     */
    protected static final Integer DEFAULT_ADVERTISING_DISPLAY = 1;

    /**
     * 用户账号状态-启用 value:1
     */
    protected static final Short USER_ENABLED_TRUE = Short.parseShort("1");

    /**
     * 用户账号状态-停用 value:0
     */
    protected static final Short USER_ENABLED_FALSE = Short.parseShort("0");
    /**
     * 企业状态信息-审核中 value:2
     */
    protected static final Short COMPANY_AUDIT_STATUS_ON = Short.parseShort("2");

    /**
     * 企业状态信息-已通过审核 value:3
     */
    protected static final Short COMPANY_AUDIT_STATUS_PASS = Short.parseShort("3");

    /**
     * 企业状态信息-未通过审核 value:4
     */
    protected static final Short COMPANY_AUDIT_STATUS_FALL = Short.parseShort("4");

    /**
     * 字符数组，用于中文汉字转拼音时，追加到不唯一的标识符后面
     */
    protected static final char[] SUFFIX_TRANSITION_PINYING = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 分页默认页码
     */
    protected static final String PAGE_NUM = "1";

    /**
     * 分页默认每页显示数据条数
     */
    protected static final String PAGE_SIZE = "10";

    // edited by quminghui on 2017-06-20 14:59 begin
    /**
     * 分页最大页码
     */
    Integer maxPage;

    /**
     * 标识符(区分home与adCountList)
     */
    String identification;
    // edited by quminghui on 2017-06-20 14:59 end

    protected JsonResult jr = new JsonResult();
    @Autowired
    protected RoleService roleService;
    @Autowired
    protected RoleAuthorityService roleAuthorityService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected OrderFinishedInfoService orderFinishedInfoService;

    @Autowired
    protected AuthorityService authorityService;

    @Autowired
    protected UserRoleService userRoleService;
    @Autowired
    protected CompanyService companyService;

    //edited by xiaokun on 2017-04-14 20:43 begin
    @Autowired
    protected HelpService helpService;
    //edited by xiaokun on 2017-04-14 20:43 end

    // edited by cuixiaokun on 2017-03-24 14:57:52 begin
    @Autowired
    protected CompanyVocationService companyVocationService;
    // edited by cuixiaokun on 2017-03-24 14:57:52 end

    @Autowired
    protected CompanyAppService companyAppService;
    @Autowired
    protected AdOrderService adOrderService;
    @Autowired
    protected OrderAdService orderAdService;
    @Autowired
    protected AdService adService;
    // edited by mxy on 2017-03-22 14:46 begin
    @Autowired
    protected AdSlotService adSlotService;
    @Autowired
    protected VideoService videoService;
    @Autowired
    protected SceneService sceneService;
    @Autowired
    protected PersonService personService;
    @Autowired
    protected ObjectService objectService;
    @Autowired
    protected SlotLabelService slotLabelService;
    @Autowired
    protected ProgramTypeService programTypeService;
    // edited by mxy on 2017-03-22 14:47 end
    @Autowired
    protected AdDisplayCountService adDisplayCountService;
    @Autowired
    protected AdStyleService adStyleService;
    // edited by mxy on 2017-03-24 21:02 begin
    @Autowired
    protected PersonRecognitionResultService personRecognitionResultService;
    @Autowired
    protected SceneRecognitionResultService sceneRecognitionResultService;
    // edited by mxy on 2017-03-24 21:02 end
    // edited by mxy on 2017-03-26 16:45 begin
    @Autowired
    protected ProgramService programService;
    // edited by mxy on 2017-03-26 16:45 end

    // edited by liuxiaolong on 2017-03-28 13:21 begin
    @Autowired
    protected AdReasonService adReasonService;

    @Autowired
    protected AdReasonTemplateService adReasonTemplateService;
    // edited by liuxiaolong on 2017-03-28 13:21 end

    // edited by xiaokun on 2017-03-29 19:31 begin
    @Autowired
    protected NoticeService noticeService;
    // edited by xiaokun on 2017-03-29 19:31 end
    // edited by liuxiaolong on 2017-03-28 13:21 end

    //edited by xiaokun on 2017-03-30 15:33 begin
    @Autowired
    protected FeedbackService feedbackService;
    //edited by xiaokun on 2017-03-30 15:33 end
    
    //edited by liuxiaolong on 2017-04-07 begin
    @Autowired
    protected AdTypeService adTypeService;
    //edited by liuxiaolong on 2017-04-07 end
    //edited by mxy on 2017-05-05 17:38 begin
	@Autowired
	protected AdvertiserService advertiserService;
    @Autowired
    protected PlatformPriceService platformPriceService;
    //edited by mxy on 2017-05-05 17:39 end

    // edited by xiaokun on 2017-06-28 09:33 begin
    @Autowired
    protected OrderAdSlotPreviewService orderAdSlotPreviewService;
    @Autowired
    protected OrderProgramPreviewService orderProgramPreviewService;
    // edited by xiaokun on 2017-06-28 09:33 end

    //edited by shixinpeng on 2017-05-09 17:39 begin
    @Autowired
    protected VideoPlayRecordService videoPlayCountService;
    //edited by shixinpeng on 2017-05-09 17:39 end
    //edited by shixinpeng on 2017-05-11 17:39 begin
    @Autowired
    protected VideoPlayRecordNoteService videoPlayRecordNoteService;
    //edited by shixinpeng on 2017-05-14 17:39 end
  
  
    /**
     * 渲染失败数据
     *
     * @return result
     */
    protected JsonResult renderError() {
        JsonResult result = new JsonResult();
        result.setSuccess(false);
        result.setStatus("500");
        return result;
    }

    /**
     * 渲染失败数据（带消息）
     *
     * @param msg
     *            需要返回的消息
     *
     * @return result
     */
    protected JsonResult renderError(String msg) {
        JsonResult result = renderError();
        result.setSuccess(false);
        result.setMsg(msg);
        result.setStatus("500");
        return result;
    }

    /**
     * 渲染成功数据
     *
     * @return result
     */
    protected JsonResult renderSuccess() {
        JsonResult result = new JsonResult();
        result.setSuccess(true);
        result.setStatus("200");
        return result;
    }

    /**
     * 渲染成功数据（带信息）
     *
     * @param msg
     *            需要返回的信息
     *
     * @return result
     */
    protected JsonResult renderSuccess(String msg) {
        JsonResult result = renderSuccess();
        result.setSuccess(true);
        result.setStatus("200");
        result.setMsg(msg);
        return result;
    }

    /**
     * @param @return
     *
     * @return Session
     *
     * @throws @Title:
     *             getSession
     * @Description: 获取session(shiro)
     * @author mengqch
     * @date 2015-10-9
     */
    public Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * @param @param
     *            key
     * @param @param
     *            value
     *
     * @return void
     *
     * @throws @Title:
     *             setSession
     * @Description: 将对象存入session
     * @author mengqch
     * @date 2015-10-9
     */
    public void setSession(String key, Object value) {
        getSession().setAttribute(key, value);
        if (log.isDebugEnabled()) {
            log.debug("存放[{}]-[{}]到HttpSession.", key, value);
        }
    }

    /**
     * @param @param
     *            key
     *
     * @return void
     *
     * @throws @Title:
     *             removeSession
     * @Description: 移除session中的对象
     * @author mengqch
     * @date 2015-10-9
     */
    public void removeSession(String key) {
        getSession().removeAttribute(key);
        if (log.isDebugEnabled()) {
            log.debug("移除[{}]到HttpSession.", key);
        }
    }

    /**
     * @param @return
     *
     * @return User
     *
     * @throws @Title:
     *             getShiroUser
     * @Description: 获取Shiro中保存的User
     * @author mengqch
     * @date 2015-10-9
     */
    public User getShiroUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * @param @return
     *
     * @return Subject
     *
     * @throws @Title:
     *             getSubject
     * @Description: 获取Shiro中保存的Subject
     * @author mxy
     * @date 2017-02-14
     */
    public Subject getSubject() {
        return (Subject) SecurityUtils.getSubject();
    }

    // edited by mxy on 2017-03-29 17:18 begin

    /**
     * @param str
     *
     * @return Integer
     *
     * @title getArrayNum
     * @description TODO 获取String转数组后除重后的元素个数
     * @author mxy
     * @date 2017-03-23 16:40
     * @modifier
     * @remark
     * @version V1.0
     */
    public Integer getArrayNum(String str) {
        if (str == null||str.equals("0")) {
            return 0;
        }
        String[] array = str.split(",");
        Set<String> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        set.remove("0");
        return set.size();
    }

    /**
     * @param prepare
     *
     * @return
     *
     * @Description: 空String转Null+去前后空格
     * @Creator mxy
     * @date 2016-6-13下午4:03:08
     * @Modifier:
     * @Date:
     * @Remark:
     * @Version: V1.0
     */
    protected String string2Null(String prepare) {

        if (prepare == null || prepare.trim().equals("") || prepare.equals("全部")) {
            prepare = null;
        } else {
            prepare = prepare.trim();
        }

        return prepare;

    }

    /**
     * @param prepare
     *
     * @return
     *
     * @Description: 空Date转Null
     * @Creator mxy
     * @date 2016-6-13下午4:03:08
     * @Modifier:
     * @Date:
     * @Remark:
     * @Version: V1.0
     */
    protected Date date2Null(Date prepare) {

        if (prepare == null || prepare.equals("") || prepare.equals("全部")) {
            prepare = null;
        }

        return prepare;

    }

    /**
     * @param prepare
     *
     * @return
     *
     * @Description: 空Integer转Null
     * @Creator mxy
     * @date 2016-6-13下午4:03:08
     * @Modifier:
     * @Date:
     * @Remark:
     * @Version: V1.0
     */
    protected Integer integer2Null(Integer prepare) {

        if (prepare == null || prepare.equals("") || prepare.equals("全部")) {
            prepare = null;
        }

        return prepare;

    }

    /**
     * @param prepare
     *
     * @return
     *
     * @Description: 空Long转Null
     * @Creator mxy
     * @date 2016-6-23下午8:08:17
     * @Modifier:
     * @Date:
     * @Remark:
     * @Version: V1.0
     */
    protected Long long2Null(Long prepare) {

        if (prepare == null || prepare.equals("") || prepare.equals("全部")) {
            prepare = null;
        }

        return prepare;

    }

    /**
     * @param prepare
     *
     * @return
     *
     * @Description: 空Boolean转Null
     * @Creator mxy
     * @date 2016-6-21上午9:31:16
     * @Modifier:
     * @Date:
     * @Remark:
     * @Version: V1.0
     */
    protected Boolean boolean2Null(Boolean prepare) {

        if (prepare == null || prepare.equals("") || prepare.equals("全部")) {
            prepare = null;
        }

        return prepare;

    }

    /**
     * @param date
     * @param day
     *
     * @return
     *
     * @Description: 获取前几天日期
     * @Creator mxy
     * @date 2016-7-26下午4:59:23
     * @Modifier:
     * @Date:
     * @Remark:
     * @Version: V1.0
     */
    public Date getDateBefore(Date date, Integer day) {
        if (day!=null) {
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.set(5, now.get(5) - day);
            return now.getTime();
        }
        return date;
    }

    /**
     * @param d
     * @param day
     *
     * @return
     *
     * @Description: 获取后几天日期
     * @Creator mxy
     * @date 2016-7-26下午4:59:23
     * @Modifier:
     * @Date:
     * @Remark:
     * @Version: V1.0
     */
    public Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(5, now.get(5) + day);
        return now.getTime();
    }

    /**
     * @param url
     *
     * @return
     *
     * @Description: 获取IP
     * @Creator mxy
     * @date 2016-11-29下午4:04:07
     * @Modifier:
     * @Date:
     * @Remark:
     * @Version: V1.0
     */
    public String getIP(String url) {
        url = url.replace("https://", "");
        url = url.replace("http://", "");
        url = url.replace("/", "");
        String ip = "";
        try {
            InetAddress inetAddress = InetAddress.getByName(url);
            ip = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            ip = "暂无";
        }
        return ip;
    }

    /**
     * @param str
     *
     * @return
     *
     * @Description: 去除string中空格及制表符
     * @Creator mxy
     * @date 2016-12-1下午6:21:01
     * @Modifier:
     * @Date:
     * @Remark:
     * @Version: V1.0
     */
    public String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * @param integer
     *
     * @return String
     *
     * @throws @title
     *             second2Time
     * @description TODO 秒转时间：120-->00:02:00
     * @author mxy
     * @date 2017-03-29 17:20
     * @modifier
     * @remark
     * @version V1.0
     */
    public String second2Time(Integer integer) {
        String result = "";
        if (integer != null) {
            int hour = integer / 3600;// 小时
            int minute = integer % 3600 / 60;// 分钟
            int second = integer % 60;// 秒
            result = (hour > 9 ? "" : "0") + hour + ":" + (minute > 9 ? "" : "0") + minute + ":"
                    + (second > 9 ? "" : "0") + second;
        }
        return result;
    }
    // edited by mxy on 2017-03-29 17:23 end
    //edited by mxy on 2017-05-05 17:53 begin
    /**
     * @param results
     * @return List<String>
     * @throws
     * @title string2List
     * @description TODO 将results参数打包成list
     * @author mxy
     * @date 2017-03-31 11:24
     * @modifier
     * @remark
     * @version V1.0
     */
    public List<String> string2List(String results) {
        //结果集
        List<String> resultList = Lists.newArrayList();
        //封装结果集
        String[] resultX = null;
        if (null != results && !results.trim().equals("")) {
            resultX = results.split(",");
        }
        if (resultX != null) {
            for (String s : resultX) {
                resultList.add(s);
            }
        }
        return resultList;
    }
    //edited by mxy on 2017-05-05 17:57 end
}
