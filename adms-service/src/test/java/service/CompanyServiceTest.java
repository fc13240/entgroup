package service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.Company;
import com.entgroup.adms.service.CompanyService;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.countMatches;

/**
 * Created with IntelliJ IDEA.
 *
 * @description 用途说明
 * User: hpb
 * Date: 2017/3/10
 */
public class CompanyServiceTest extends BaseServiceTest<CompanyService> {

    /**
     * void
     *
     * @title getCompanyList
     * @description TODO
     * @author mxy
     * @date 2017-03-23 15:47
     * @modifier
     * @remark
     * @version V1.0
     * @throws IOException
     */
    @Test
    public void getCompanyList() throws IOException {
        List<Company> CompanyList = service.getContentPlatformList();
        System.out.println(CompanyList);
    }

    /**
     * @Title: CompanyServiceTest
     * @Description: 获取客户列表-分页功能测试
     * @author xiaokun
     * @date 2017/3/24
     * @param  @param null
     * @return
     * @throws
     */
    @Test
    public void testGetAllCompanies() throws IOException {
        Page<Company> page = new Page<>(1,2);
        Company company = new Company();
        page = service.getAllCompanies(page, company);
        for (Company companyTemp : page.getRecords()) {
            System.err.println(JSON.toJSONString(companyTemp));
        }
    }

    /**
     * @title testCountMatches
     * @description TODO 测试字符串获取
     * @author xiaokun
     * @date 2017-04-14 19:35
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param
     * @return void
     * @throws
     */
    @Test
    public void testCountMatches() throws Exception {
        String status = "1,2,2,2,1,1,1,1,1,1,1,1,1,2";
        String sub = "1";
        System.err.println("进行中:"+ countMatches(status, sub)+"\n\n订单数:"+(countMatches(status, ",")+1));
    }

    @Test
    public void testGetListByLike() {
        List<Company> companyList = service.selectList(new EntityWrapper<Company>().like("company_name", "艺"));
        System.err.println("LIST:"+companyList);
    }
}
