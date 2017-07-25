package com.entgroup.adms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author mengqch
 * @ClassName: InitServletContextListener
 * @Description: 自定义监听器，容器加载时查询相关数据
 * @date 2015-11-29
 */
@WebListener
public class InitServletContextListener implements ServletContextListener {

    protected Logger log = LoggerFactory.getLogger(getClass());

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();

        log.info("initServletContextListener......");


    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}

