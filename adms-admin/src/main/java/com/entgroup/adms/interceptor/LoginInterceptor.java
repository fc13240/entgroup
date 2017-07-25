package com.entgroup.adms.interceptor;

import com.entgroup.adms.conf.AdminConfig;
import com.entgroup.adms.conf.AdmsConstants;
import com.entgroup.adms.exception.ADMSException;
import com.entgroup.adms.model.system.User;
import com.entgroup.adms.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mengqch
 * @ClassName: LoginInterceptor
 * @Description: 登录拦截器
 * @date 2015-10-2
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    //是否开启性能监控
    private static boolean usePerformance = StringUtils.equals("Y", AdminConfig.config.getUseperformancemonitor());

    private static ThreadLocal<StopWatch> stopWatchLocal = new ThreadLocal<StopWatch>();

    private static final Pattern pattern = Pattern
            .compile("script|select|update|delete|insert|drop|truncate|create|or|and|alert",
                    Pattern.CASE_INSENSITIVE);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if (usePerformance) {
                StopWatch stopWatch = new StopWatch(handler.toString());
                stopWatchLocal.set(stopWatch);
                stopWatch.start(handler.toString());
            }

            Subject subject = SecurityUtils.getSubject();

            if (null != subject && subject.isAuthenticated()) {
                User user = (User) subject.getPrincipal();
                Session session = subject.getSession();
                if (null == session.getAttribute(AdmsConstants.SESSION_USER_KEY)) {
                    // 把当前登陆用户放入session中
                    session.setAttribute(AdmsConstants.SESSION_USER_KEY, user);

                    if (log.isDebugEnabled()) {
                        log.debug("set session[{}]=[{}]", AdmsConstants.SESSION_USER_KEY, user);
                    }
                }
            }

            // sql注入检查
//			sqlInjectCheck(request);

//		} catch (ADMSException ge) {
//			log.warn("拦截器处理异常 {}", ge.getMessage());
//			throw ge;
        } catch (Exception e) {
            log.warn("拦截器处理异常" + e.getMessage());
            throw e;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (usePerformance) {
            StopWatch stopWatch = stopWatchLocal.get();
            stopWatch.stop();
            String currentPath = request.getRequestURI();
            String queryString = request.getQueryString();
            queryString = queryString == null ? "" : "?" + queryString;
            if (stopWatch.getTotalTimeMillis() > 2000L) {
                log.warn("发现性能异常： access url path: {} | time: {}", currentPath + queryString,
                        stopWatch.getTotalTimeMillis());
            }

            stopWatchLocal.set(null);
        }
    }

    private void sqlInjectCheck(HttpServletRequest request) throws ADMSException {
        String ip = HttpUtil.getClientIp(request);
        Enumeration<String> e = request.getParameterNames();
        String key = null;
        String[] values = null;
        int count = 0;

        String requestParam = request.getRequestURI().toString();
        if (requestParam.contains("sewise") || requestParam.contains("/http:")) {
            return;
        }

        while (e.hasMoreElements() && count < 200) {
            count++;
            key = e.nextElement();
            values = request.getParameterValues(key);
            if (values != null && values.length > 0) {
                for (int i = 0; i < values.length; i++) {
                    Matcher matcher = pattern.matcher(values[i]);
                    if (matcher.find()) {
                        log.warn(ip + " " + request.getRequestURL().toString() + " "
                                + request.getHeader("User-Agent") + " " + " " + "传入被禁止的参数" + values[i] + " 全部参数列表："
                                + HttpUtil.getRequestParameters(request, "UTF-8")
                                + "\r\n----------------------------------------------------------------------------");
                        throw new ADMSException("200007", "非法的参数传递");
                    }
                }
            }
        }
    }
}

