/**
 * @Title: SystemLogAspect.java
 * @Description: TODO(用一句话描述该文件做什么)
 * @author mengqch
 * @version V1.0
 */
package com.entgroup.adms.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author mengqch
 * @ClassName: SystemLogAspect
 * @Description: 切点类
 * @date 2015-10-1 下午12:09:23
 */
@Aspect
@Component
public class SystemAspect {

//	@Resource
//	SyslogService logService;

    protected Logger log = LoggerFactory.getLogger(SystemAspect.class);

    /**
     * @param
     * @return void
     * @throws
     * @Title: controllerAspect
     * @Description: controller层切点
     * @author mengqch
     * @date 2015-10-1
     */
    @Pointcut("@annotation(com.entgroup.adms.aop.SystemControllerLog)")
    public void controllerAspect() {

    }

    /**
     * @param
     * @return void
     * @throws
     * @Title: serviceAspect
     * @Description: service层切点
     * @author mengqch
     * @date 2015-10-1
     */
    @Pointcut("@annotation(com.entgroup.adms.aop.SystemServiceLog)")
    public void serviceAspect() {

    }

    /**
     *
     * @Title: executeBefore
     * @Description: 前置通知，用于拦截controller层的用户操作
     * @author mengqch
     * @date 2015-10-1
     * @param @param joinPoint
     * @return void
     * @throws
     */
//	@Before("controllerAspect()")
//	public void executeBefore(JoinPoint joinPoint) {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//		HttpSession session = request.getSession();
//
//		// 读取session中的用户
//		User user = (User) session.getAttribute(AdmsConstants.SESSION_USER_KEY);
//		try {
//			Long companyId = 0L;
//			Long userId = 0L;
//			String loginName = AdmsConstants.UNLOGIN_USER_NAME;
//			//FIXME 根据用户角色判断类型
//			Short userType = Short.parseShort("1");
//			// 请求的IP
//			String requestIp = HttpUtil.getClientIp(request);
//
//			if (null != user) {
//				companyId = user.getCompanyId();
//				userId = user.getId();
//				loginName = user.getLoginName();
//			} 
//			
//			Syslog syslog = new Syslog();
//			syslog.setCompanyId(companyId);
//			syslog.setUserId(userId);
//			syslog.setLoginName(loginName);
//			
//			String annotaions[] = getControllerMethodAnnotations(joinPoint);
//			syslog.setSysModule(annotaions[0]);
//			syslog.setActionDescription(annotaions[1]);
//			
//			syslog.setActionTime(new Date());
//			syslog.setUserType(userType);
//			syslog.setRequestIp(requestIp);
//			syslog.setClassName(joinPoint.getTarget().getClass().getName());
//			syslog.setMethod(joinPoint.getSignature().getName());
//			
//			// TODO 可以取相对重要的参数存入数据库
//			//获取用户请求方法的参数并序列化为JSON格式字符串    
////			String params = "";    
////			if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
////				for ( int i = 0; i < joinPoint.getArgs().length; i++) {    
////					params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";    
////				}    
////			}    
////
////			syslog.setParams(params);
//			
//			logService.insertSelective(syslog);
//			
//		} catch (ADMSException e) {
//			log.error(e.getMessage());
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//
//	}

//	@After("controllerAspect()")
//	public void executeAfter(JoinPoint joinPoint) {
//		System.out.println("finished.....");
//	}

    /**
     * @param @param joinPoint
     * @param @param e
     * @return void
     * @throws
     * @Title: executeAfterThrowing
     * @Description: 异常通知，用于拦截service层记录异常日志
     * @author mengqch
     * @date 2015-10-1
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void executeAfterThrowing(JoinPoint joinPoint, Throwable e) {
        //TODO 后期可以强化service层异常捕获，写数据库或者写入日志文件
    }

    /**
     * @param @param  joinPoint
     * @param @return
     * @param @throws Exception
     * @return String
     * @throws
     * @Title: getControllerMethodDescription
     * @Description: 获取注解中对方法的描述信息，用于controller层注解
     * @author mengqch
     * @date 2015-10-1
     */
    public static String[] getControllerMethodAnnotations(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String annotations[] = new String[2];
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    annotations[0] = method.getAnnotation(SystemControllerLog.class).moduleName()[0];
                    annotations[1] = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return annotations;
    }

    /**
     * @param @param  joinPoint
     * @param @return
     * @param @throws Exception
     * @return String
     * @throws
     * @Title: getServiceMthodDescription
     * @Description: 获取注解中对方法的描述信息 用于service层注解
     * @author mengqch
     * @date 2015-10-1
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
}
