package com.entgroup.adms.conf;

/**
 * @ClassName: Constants
 * @Description: 项目中用到的常量
 * @author mengqch
 */
public class AdmsConstants
{

	/**
	 * Session 中user的key
	 */
	public static final String SESSION_USER_KEY = "loginUser";
	
	/**
	 * 登录用户的会话sessionId
	 */
	public static final String SESSION_ID_KEY="session_id_key";
	
	/**
	 * 登录用户 通信连接ID
	 */
	public static final String CONNECT_ID_KEY="connect_id_key";
	
	/**
	 * 未登录用户默认显示名称
	 */
	public static final String UNLOGIN_USER_NAME = "未登录用户";
	
	/**
	 * 电影及综艺展位最大值
	 */
	public static final Integer MAX_ADSIZE_MOVIE = 6;
	
	/**
	 * 电视剧展位最大值
	 */
	public static final Integer MAX_ADSIZE_TELEPLAY = 3;
	
	/**
	 * 
	 * @ClassName: SysModule 
	 * @Description: 系统模块
	 * @author mengqch 
	 * @date 2015-10-1
	 */
	public enum SysModule {
        /** 系统日志*/
        MODULE_SYSLOG {public String value(){return "系统日志";}},
        /** 角色管理 */
        MODULE_ROLE {public String value(){return "角色管理";}},
        /** 用户管理 */
        MODULE_USER {public String value(){return "用户管理";}},
        /** 广告主管理 */
        MODULE_ADERS {public String value(){return "广告主管理";}},
        /** SDK管理 */
        MODULE_SDK {public String value(){return "SDK管理";}},
        /** 广告中心 */
        MODULE_AD {public String value(){return "广告中心";}},
        /** 财务中心 */
        MODULE_MONEY {public String value(){return "财务中心";}},
        /** 数据中心 */
        MODULE_DATA {public String value(){return "数据中心";}},
        /** 个人中心 */
        MODULE_PERSONAL {public String value(){return "个人中心";}};
        
        public abstract String value();
	}
	
}
