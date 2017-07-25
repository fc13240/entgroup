package service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.User;
import com.entgroup.adms.service.UserService;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @description 用途说明
 * User: hpb
 * Date: 2017/2/28
 */

public class UserServiceTest  extends BaseServiceTest<UserService>{



//    @Test
    public void testInsert() throws IOException {
        User user = new User();
        user.setLoginName("test123");
        user.setPassword("123");
        boolean result = service.insert(user);
        logger.info("insertResult: " + result);
    }

//    @Test
    public void testInsertBatch() throws IOException {
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setLoginName("test123" + i);
            user.setPassword("123" + i);
            userList.add(user);
        }
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(new User());
        boolean batchResult = service.insertBatch(userList);
        System.err.println("batchResult: " + batchResult);
    }

    /**
     * @throws java.io.IOException
     */
    @Test
    public void testPage() throws IOException {
        EntityWrapper<User> entityWrapper = new EntityWrapper<>(new User());
        entityWrapper.like("login_name", "12")//column为数据库字段
                .orderBy("id desc")
                .between("id", "1", "10");
        List<User> userList = service.selectList(entityWrapper);
        System.err.println("no page:" + userList.size());
        Page<User> page = new Page<User>(1, 5);
        page = service.selectPage(page, entityWrapper);
        System.err.println(" page 1:" + page.toString());
        page = new Page<User>(2, 5);
        page = service.selectPage(page, entityWrapper);
        System.err.println(" page 2:" + page.toString());


//        entityWrapper.where("login_name={0}", "'123'").and("id=1")
////                .groupBy("x1").groupBy("x2,x3")
////                .having("x1=11").having("x3=433")
//                .orderBy("id").orderBy("d1,d2");

    }
    /**
     * @throws java.io.IOException
     */
    @Test
    /**
     * @title testLogin
     * @description TODO
     * @param
     * @return void
     * @throws 
     * @author mxy
     * @date 2017-57-16 13:57
     * @modifier
     * @remark
     * @version V1.0
     */
    public void testLogin() throws IOException {
        EntityWrapper<User> entityWrapper = new EntityWrapper<>(new User());
        entityWrapper.like("login_name", "admin@xiaobai.com");//colum数据库字段
        User user = service.selectOne(entityWrapper);
        //2017-03-16 09:04:18.166 [main] INFO  c.e.adms.interceptor.SqlPerformanceInterceptor -  Time：136ms,sql ID:com.entgroup.adms.mapper.UserMapper.selectList, Execute SQL：SELECT id,login_name AS loginName,`password`,real_name AS realName,mobile,phone_number AS phoneNumber,email,qq,company_id AS companyId,is_admin AS isAdmin,enabled,deleted,remark,salt,last_login_date AS lastLoginDate,manager,creator,created,modifier,updated,head_portrait AS headPortrait,last_login_ip AS lastLoginIp FROM sys_user WHERE (login_name LIKE '%admin@xiaobai.com%')
        //2017-03-16 09:09:33.688 [main] DEBUG com.entgroup.adms.mapper.UserMapper.selectList - ==>  Preparing: SELECT id,login_name AS loginName,`password`,real_name AS realName,mobile,phone_number AS phoneNumber,email,qq,company_id AS companyId,is_admin AS isAdmin,enabled,deleted,remark,salt,last_login_date AS lastLoginDate,manager,creator,created,modifier,updated,head_portrait AS headPortrait,last_login_ip AS lastLoginIp,company FROM sys_user WHERE (login_name LIKE '%admin@xiaobai.com%')
        System.out.println(user);
        User user2 = service.findUserInfoByLoginName("admin@xiaobai.com");
        System.out.println(user2);

    }

    /**
    * @Title: testGetAllUsers
    * @Description: 获取用户列表方法测试
    * @author xiaokun
    * @date 2017/3/23
    * @param
    * @return
    * @throws
    */
    @Test
    public void testGetAllUsers() throws IOException {
        Page<User> userPage = new Page<>(1,10);
        userPage.setOrderByField("id");
        userPage.setAsc(false);
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        User user = null;
        userPage = service.getAllUsers(userPage, user);
        System.err.println(userPage);
        System.out.println("***********************************************************************");
        for (User userTemp : userPage.getRecords()) {
            System.out.println(JSON.toJSONString(userTemp));
        }
    }

    /**
     * @title testGetOne
     * @description TODO 获取单独用户接口测试
     * @author xiaokun
     * @date 2017-03-19 09:00
     * @modifier
     * @remark
     * @version V1.0
     *
     * @return void
     * @throws Exception
     */
    @Test
    public void testGetOne() throws Exception {
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("company_id", 1L).eq("admin", 1);
        System.err.println(JSON.toJSONString(service.selectOne(userEntityWrapper)));
    }

    /**
     * @title testCheckPassword
     * @description TODO 密码校验测试
     * @author xiaokun
     * @date 2017-03-29 16:06
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param
     * @return void
     * @throws Exception
     */
    @Test
    public void testCheckPassword() throws Exception {
        User user = service.selectById(1L);
        String password = "admsadmin";
        System.err.println(service.checkPassword(user, password));
    }

    /**
     * @title testEntityWrapper
     * @description TODO 测试EntityWrapper的使用
     * @author xiaokun
     * @date 2017-03-30 14:20
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param
     * @return void
     * @throws Exception
     */
    @Test
    public void testEntityWrapper() throws Exception {
        EntityWrapper<User> enNull = new EntityWrapper<>();
        EntityWrapper<User> paNull = new EntityWrapper<>();
        paNull.eq("last_login_date", null);

        List<User> userList = service.selectList(null);
        System.err.println("RESULT_FOR_NULL : "+JSON.toJSONString(userList)+"\n\n******************************************************");
        userList = service.selectList(enNull);
        System.err.println("RESULT_FOR_ENULL : "+JSON.toJSONString(userList)+"\n\n******************************************************");
        userList = service.selectList(paNull);
        System.err.println("RESULT_FOR_PNULL : "+JSON.toJSONString(userList)+"\n\n******************************************************");
    }
}

