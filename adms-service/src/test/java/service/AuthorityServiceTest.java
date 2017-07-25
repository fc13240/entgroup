package service;

import com.entgroup.adms.model.system.Authority;
import com.entgroup.adms.service.AuthorityService;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @description 用途说明
 * User: hpb
 * Date: 2017/3/10
 */
public class AuthorityServiceTest {
    private AuthorityService authorityService;
    private final Logger logger = LoggerFactory.getLogger(AuthorityServiceTest.class);


    @Before
    public void init() {

        ApplicationContext aCtx = new FileSystemXmlApplicationContext(
                "classpath:testContext.xml");
        authorityService = aCtx
                .getBean(AuthorityService.class);

    }

    @Test
    public void testGetAll() throws Exception {

        logger.info("first query.....");
        List<Authority> authorities = authorityService.selectAll();

        logger.info("second query....");
        List<Authority> cacheAuthorities = authorityService.selectAll();
        logger.info("end");
    }
}
