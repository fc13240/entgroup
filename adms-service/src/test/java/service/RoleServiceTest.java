package service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.Role;
import com.entgroup.adms.service.RoleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author xiaokun
 * @Title RoleServiceTest
 * @description 角色管理测试类
 * @create 2017/3/27
 * @param:
 * @return
 * @throws
 */
public class RoleServiceTest extends BaseServiceTest<RoleService> {

    /**
    * @Title: testGetAllRoles
    * @Description: 获取权限列表
    * @author xiaokun
    * @date 2017/3/27
    * @param
    * @return
    * @throws
    */
    @Test
    public void testGetAllRoles() {
        Role role = new Role();
        role.setCompanyId(1L);
        Page<Role> rolePage = new Page<>(1,2);
        rolePage = service.getAllRoles(rolePage, role);
        for (Role roleTemp : rolePage.getRecords()) {
            System.err.println(JSON.toJSONString(roleTemp));
        }
    }

    /**
     * @title testGetByNotIn
     * @description TODO
     * @author xiaokun
     * @date 2017-04-13 09:11
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param
     * @return void
     * @throws Exception
     */
    @Test
    public void testGetByNotIn() throws Exception {
        List<Role> roleList = service.selectList(new EntityWrapper<Role>().notIn("id", "1,2"));
        roleList = service.selectList(new EntityWrapper<Role>().ne("id", "1"));
        for (Role role : roleList) {
            System.err.println("* :"+role.toString());
        }
    }
}
