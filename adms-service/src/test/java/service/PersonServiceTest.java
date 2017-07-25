package service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entgroup.adms.model.TreeNode;
import com.entgroup.adms.model.system.AdSlot;
import com.entgroup.adms.model.system.Person;
import com.entgroup.adms.service.AdSlotService;
import com.entgroup.adms.service.PersonService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.IOException;
import java.util.List;
/**
 * @author mxy
 * @ClassName: PersonServiceTest
 * @Description: 
 * @date 2017-03-27 16:02
 */
public class PersonServiceTest extends BaseServiceTest<PersonService>{

    /**
     * void
     *
     * @title getPerson4AdsList
     * @description TODO
     * @author mxy
     * @date 2017-03-27 16:07
     * @modifier
     * @remark
     * @version V1.0
     * @throws IOException
     */
    @Test
    public void getPerson4AdsList() throws IOException {
        List<Person> PersonList = service.getPerson4AdsList(0L,null,null);
        System.out.println(PersonList);
    }
    protected AdSlotService adSlotService;
    /**
     * void
     *
     * @title getPersonList4TreeNode
     * @description TODO
     * @author mxy
     * @date 2017-03-22 15:27
     * @modifier
     * @remark
     * @version V1.0
     * @throws IOException
     */
    @Test
    public void getPersonList4TreeNode() throws IOException {
        ApplicationContext aCtx = new FileSystemXmlApplicationContext(
                "classpath:testContext.xml");
        adSlotService = aCtx.getBean(AdSlotService.class);
        //查询当前广告位已有标签
        AdSlot adSlot = adSlotService.selectById(103L);
        // may be null
        String[] personIds = null;

        if (null != adSlot) {
            personIds = adSlot.getPersonIds() == null ? null : adSlot.getPersonIds().split(",");
        }
        //     查询所有的场景,并封装成treeNode对象,并把当前角色已有的权限选中
        List <Person> persons = service.selectList(new EntityWrapper<Person>());
        List<TreeNode> treeNodes = Lists.newArrayList();
        for (Person person : persons) {
            Boolean mark = Boolean.FALSE;
            if (ArrayUtils.indexOf(personIds, person.getId()+"") > -1) {
                mark = Boolean.TRUE;
            }
            treeNodes.add(new TreeNode(person.getId().intValue(),null,person.getName(),mark));
        }
        System.out.println(treeNodes);

    }
}

