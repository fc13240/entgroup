package service;

import com.entgroup.adms.model.system.CompanyApp;
import com.entgroup.adms.service.CompanyAppService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * Created with IntelliJ IDEA.
 *
 * @description 用途说明
 * User: hpb
 * Date: 2017/3/10
 */
public class CompanyAppServiceTest extends BaseServiceTest<CompanyAppService> {

    CacheManager cacheManager;

    @Override
    public void init() {
        super.init();
        cacheManager = aCtx.getBean(CacheManager.class);
    }

    @Test
    public void testSelectByAppPackage() throws Exception {

        logger.info("first query.....");
        service.selectByAppPackage("com.xiaobai");

        logger.info("second query....");
        service.selectByAppPackage("com.xiaobai");
        Cache cache = cacheManager.getCache("appCache");
        CompanyApp app = cache.get("com.xiaobai", CompanyApp.class);
        logger.info("read from cache:{}", app.getId());

    }


}
