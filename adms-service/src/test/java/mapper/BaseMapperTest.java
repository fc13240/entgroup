package mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.toolkit.ReflectionKit;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.beans.Introspector;

/**
 * Created with IntelliJ IDEA.
 *
 * @description 用途说明
 * User: hpb
 * Date: 2017/3/10
 */
public class BaseMapperTest<M extends BaseMapper> {

    protected M mapper;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void init() {

        ApplicationContext aCtx = new FileSystemXmlApplicationContext(
                "classpath:testContext.xml");
        Class<?> cls = ReflectionKit.getSuperClassGenricType(getClass(), 0);
        mapper = (M) aCtx
                .getBean(Introspector.decapitalize(cls.getSimpleName()));

    }
}
