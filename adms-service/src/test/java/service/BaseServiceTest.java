package service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.toolkit.ReflectionKit;
import com.entgroup.adms.service.UserService;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.beans.Introspector;

/**
 * Created with IntelliJ IDEA.
 *
 * @description 用途说明
 * User: hpb
 * Date: 2017/3/10
 */
public class BaseServiceTest<S extends IService> {

    protected S service;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected ApplicationContext aCtx;

    @Before
    public void init() {

        aCtx = new FileSystemXmlApplicationContext(
                "classpath:testContext.xml");
        Class<?> cls = ReflectionKit.getSuperClassGenricType(getClass(), 0);
        service = (S) aCtx
                .getBean(Introspector.decapitalize(cls.getSimpleName()));

    }
}
